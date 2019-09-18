package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.mail.Mail
import com.deathstar.competitionmanager.domain.mail.MailTemplate
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.service.user.UserService
import groovy.util.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Log
@Service
class MailServiceImpl implements MailService {

    @Autowired
    MailSender mailSender

    @Autowired
    UserService userService

    @Autowired
    MailTemplateService mailTemplateService

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    @Override
    void sentMailAboutAproveRegistration(User approvedUser) {
        MailTemplate approveUserMailTemplate = mailTemplateService.findMailTemplateByKeyName('approve_user')
        Mail mailWithApprove = new Mail(toAddress: approvedUser.mail, topic: 'Подтверждение регистрации', body: approveUserMailTemplate.template)
        mailSender.sendMail(mailWithApprove)
        log.info("Notified user ${approvedUser.id} about registration aprove")
    }

    @Override
    void notifyAllCoachesAboutNewCompetition(Competition competition) {
        MailTemplate newCompetitionMailTemplate = mailTemplateService.findMailTemplateByKeyName('new_competition')
        String notifyMailBody = newCompetitionMailTemplate.template

        newCompetitionMailTemplate.mailTemplateReplacements.forEach { mailTemplateReplacement ->
            def fieldValue = competition[mailTemplateReplacement.fieldName]
            if (LocalDate == fieldValue.getClass()) {

                fieldValue = (fieldValue as LocalDate).format(dateTimeFormatter)
            }
            notifyMailBody = notifyMailBody.replace("{${mailTemplateReplacement.key}}", fieldValue.toString())
        }

        new Thread(new Runnable() {
            @Override
            void run() {
                userService.getAllUsers()
                        .collect { user -> user.mail }
                        .unique()
                        .collect { userMailAddress -> new Mail(topic: "Новое соревнование", toAddress: userMailAddress, body: notifyMailBody) }
                        .forEach { mail -> mailSender.sendMail(mail) }

                log.info("Notified all users about new competition")
            }
        }).start()
    }
}
