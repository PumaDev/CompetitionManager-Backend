package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.view.mail.MailTemplateView
import com.deathstar.competitionmanager.view.mail.UpdateTemplateInMailTemplateView

interface MailTemplateViewService {

    List<MailTemplateView> readAllMailTemplates()

    MailTemplateView findMailTemplateById(Integer mailTemplateId)

    MailTemplateView createMailTemplate(MailTemplateView newMailTemplateView)

    MailTemplateView updateTemplateInMailTemplate(Integer mailTemplateId, UpdateTemplateInMailTemplateView updateTemplateInMailTemplateView)

    MailTemplateView updateMailTemplate(MailTemplateView mailTemplateViewWithUpdates)

    MailTemplateView deleteMailTemplateById(Integer mailTemplate)
}
