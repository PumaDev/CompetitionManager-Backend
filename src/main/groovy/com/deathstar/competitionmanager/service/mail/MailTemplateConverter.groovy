package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.MailTemplate
import com.deathstar.competitionmanager.domain.mail.MailTemplateReplacment
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
        mailTemplates.collect { mailTemplate ->
            new MailTemplateView(
                    id: mailTemplate.id,
                    keyName: mailTemplate.keyName,
                    template: mailTemplate.template
            )
        }
    }

    MailTemplate convertMailTemplateViewToMailTemplate(MailTemplateView mailTemplateView) {
        new MailTemplate(
                id: mailTemplateView.id,
                template: mailTemplateView.template,
                keyName: mailTemplateView.keyName,
                mailTemplateReplacements: mailTemplateView.mailTemplateReplacements.collect { mailTemplateReplacementView ->
                    new MailTemplateReplacment(
                            id: mailTemplateReplacementView.id,
                            key: mailTemplateReplacementView.key,
                            fieldName: mailTemplateReplacementView.fieldName
                    )
                }
        )
    }
}
