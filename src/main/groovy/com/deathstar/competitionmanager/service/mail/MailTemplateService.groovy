package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.MailTemplate

interface MailTemplateService {

    MailTemplate createMailTemplate(MailTemplate mailTemplate)

    MailTemplate updateMailTemplate(MailTemplate mailTemplate)

    MailTemplate deleteMailTemplateById(Integer mailTemplateId)

    MailTemplate findMailTemplateByKeyName(String keyName)

    MailTemplate getMailTemplateById(Integer id)

    List<MailTemplate> readAllMailTemplates()

}