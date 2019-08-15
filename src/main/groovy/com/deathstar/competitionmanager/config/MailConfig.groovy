package com.deathstar.competitionmanager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.mail.Session
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

@Configuration
class MailConfig {

    @Value('${competition-manager.mails.login}')
    String mailLogin

    @Value('${competition-manager.mails.password}')
    String mailPassword

    @Value('${competition-manager.mails.host}')
    String mailHost

    @Bean
    Session session() {
        Properties prop = new Properties()
        prop.put("mail.smtp.auth", true)
        prop.put("mail.smtp.starttls.enable", "true")
        prop.put("mail.smtp.host", mailHost)
        prop.put("mail.smtp.port", "25")
        prop.put("mail.smtp.ssl.trust", mailHost)

        Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailLogin, mailPassword)
            }
        })
    }
}
