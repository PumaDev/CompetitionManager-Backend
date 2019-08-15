package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.Mail

interface MailSender {

    void sendMail(Mail mail)
}