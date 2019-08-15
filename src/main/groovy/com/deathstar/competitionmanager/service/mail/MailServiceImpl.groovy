package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.mail.Mail
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.service.user.UserService
import groovy.util.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import java.time.format.DateTimeFormatter

@Log
@Service
class MailServiceImpl implements MailService {

    @Autowired
    MailSender mailSender

    @Autowired
    UserService userService

    @Value('${competition-manager.mails.templates.aprove}')
    String approveMailBody

    @Value('${competition-manager.mails.templates.new-competition}')
    String newCompetitionNotifyMailBody

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    @Override
    void sentMailAboutAproveRegistration(User approvedUser) {
        Mail mailWithApprove = new Mail(toAddress: approvedUser.mail, topic: 'Подтверждение регистрации', body: approveMailBody)
        mailSender.sendMail(mailWithApprove)
        log.info("Notified user ${approvedUser.id} about registration aprove")
    }

    @Override
    void notifyAllCoachesAboutNewCompetition(Competition competition) {
        String notifyMailBody = newCompetitionNotifyMailBody
                .replace('{competitionName}', competition.name)
                .replace('{startDate}', competition.startDate.format(dateTimeFormatter))

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
