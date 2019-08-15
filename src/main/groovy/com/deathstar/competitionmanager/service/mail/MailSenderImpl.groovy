package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.Mail
import com.deathstar.competitionmanager.util.enablingbyproperty.EnableByProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

import javax.mail.Message
import javax.mail.Multipart
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

@Service
class MailSenderImpl implements MailSender {

    @Value('${competition-manager.mails.login}')
    String fromMailAccount

    @Value('${competition-manager.mails.type:text/html}')
    String mailType

    @Autowired
    Session mailSession

    @Override
    @EnableByProperty('competition-manager.mails.enable')
    void sendMail(Mail mail) {
        Message message = new MimeMessage(mailSession)
        message.setFrom(new InternetAddress(fromMailAccount))
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(mail.toAddress))
        message.setSubject(mail.topic)

        MimeBodyPart mimeBodyPart = new MimeBodyPart()
        mimeBodyPart.setContent(mail.body, mailType)

        Multipart multipart = new MimeMultipart()
        multipart.addBodyPart(mimeBodyPart)

        message.setContent(multipart)

        Transport.send(message)
    }
}
