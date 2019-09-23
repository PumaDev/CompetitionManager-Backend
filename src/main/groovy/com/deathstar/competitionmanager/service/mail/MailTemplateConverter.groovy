package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.MailTemplate
import com.deathstar.competitionmanager.domain.mail.MailTemplateReplacement
import com.deathstar.competitionmanager.view.mail.MailTemplateReplacementView
import com.deathstar.competitionmanager.view.mail.MailTemplateView
import org.springframework.stereotype.Component

@Component
class MailTemplateConverter {

    MailTemplateView convertMailTemplateToView(MailTemplate mailTemplate) {
        new MailTemplateView(
                id: mailTemplate.id,
                keyName: mailTemplate.keyName,
                template: mailTemplate.template,
                mailTemplateReplacements: mailTemplate.mailTemplateReplacements.collect { mailTemplateReplacement ->
                    new MailTemplateReplacementView(
                            id: mailTemplateReplacement.id,
                            key: mailTemplateReplacement.key,
                            fieldName: mailTemplateReplacement.fieldName
                    )
                }
        )
    }

    List<MailTemplateView> convertListMailTemplatesToViews(List<MailTemplate> mailTemplates) {
        mailTemplates.collect { mailTemplate -> convertMailTemplateToView(mailTemplate) }
    }

    MailTemplate convertMailTemplateViewToMailTemplate(MailTemplateView mailTemplateView) {
        MailTemplate mailTemplate = new MailTemplate(
                id: mailTemplateView.id,
                template: mailTemplateView.template,
                keyName: mailTemplateView.keyName
        )

        mailTemplate.mailTemplateReplacements = mailTemplateView.mailTemplateReplacements.collect { mailTemplateReplacementView ->
            new MailTemplateReplacement(
                    id: mailTemplateReplacementView.id,
                    key: mailTemplateReplacementView.key,
                    fieldName: mailTemplateReplacementView.fieldName,
                    mailTemplate: mailTemplate
            )
        }

        return mailTemplate
    }
}
