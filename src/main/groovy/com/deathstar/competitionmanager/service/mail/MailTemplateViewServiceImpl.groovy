package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.MailTemplate
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.view.mail.MailTemplateView
import com.deathstar.competitionmanager.view.mail.UpdateTemplateInMailTemplateView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MailTemplateViewServiceImpl implements MailTemplateViewService {

    @Autowired
    MailTemplateService mailTemplateService

    @Autowired
    MailTemplateConverter mailTemplateConverter

    @Override
    List<MailTemplateView> readAllMailTemplates() {
        List<MailTemplate> allMailTemplates = mailTemplateService.findAll()
        return mailTemplateConverter.convertListMailTemplatesToViews(allMailTemplates)
    }

    @Override
    MailTemplateView findMailTemplateById(Integer mailTemplateId) {
        MailTemplate foundMailTemplate = mailTemplateService.getMailTemplateById(mailTemplateId)
        return foundMailTemplate ? mailTemplateConverter.convertMailTemplateToView(foundMailTemplate) : null
    }

    @Override
    MailTemplateView createMailTemplate(MailTemplateView newMailTemplateView) {
        MailTemplate newMailTemplate = mailTemplateConverter.convertMailTemplateViewToMailTemplate(newMailTemplateView)
        MailTemplate createdMailTemplate = mailTemplateService.createMailTemplate(newMailTemplate)
        return mailTemplateConverter.convertMailTemplateToView(createdMailTemplate)
    }

    @Override
    MailTemplateView updateTemplateInMailTemplate(Integer mailTemplateId, UpdateTemplateInMailTemplateView updateTemplateInMailTemplateView) {
        MailTemplate foundMailTemplate = mailTemplateService.getMailTemplateById(mailTemplateId)
        if (!foundMailTemplate) {
            throw new EntityNotFoundException("MailTemplate with id ${mailTemplateId} was not found")
        }

        foundMailTemplate.template = updateTemplateInMailTemplateView.newTemplate
        MailTemplate updatedMailTemplate = mailTemplateService.updateMailTemplate(foundMailTemplate)
        return mailTemplateConverter.convertMailTemplateToView(updatedMailTemplate)
    }

    @Override
    MailTemplateView updateMailTemplate(MailTemplateView mailTemplateViewWithUpdates) {
        MailTemplate foundMailTemplate = mailTemplateService.getMailTemplateById(mailTemplateViewWithUpdates.id)
        if (!foundMailTemplate) {
            throw new EntityNotFoundException("MailTemplate with id ${mailTemplateViewWithUpdates.id} was not found")
        }
        MailTemplate mailTemplateWithUpdates = mailTemplateConverter.convertMailTemplateViewToMailTemplate(mailTemplateViewWithUpdates)
        MailTemplate updatedMailTemplate = mailTemplateService.updateMailTemplate(mailTemplateWithUpdates)
        return mailTemplateConverter.convertMailTemplateToView(updatedMailTemplate)
    }

    @Override
    MailTemplateView deleteMailTemplateById(Integer mailTemplateIdForDelete) {
        MailTemplate deletedMailTemplate = mailTemplateService.deleteMailTemplateById(mailTemplateIdForDelete)
        return deletedMailTemplate ? mailTemplateConverter.convertMailTemplateToView(deletedMailTemplate) : null
    }
}
