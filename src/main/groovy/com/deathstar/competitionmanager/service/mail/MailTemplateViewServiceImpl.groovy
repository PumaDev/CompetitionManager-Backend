package com.deathstar.competitionmanager.service.mail


import com.deathstar.competitionmanager.domain.mail.MailTemplate
import com.deathstar.competitionmanager.domain.mail.MailTemplateReplacement
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

    @Autowired
    MailTemplateReplacementService mailTemplateReplacementService

    @Override
    List<MailTemplateView> readAllMailTemplates() {
        List<MailTemplate> allMailTemplates = mailTemplateService.readAllMailTemplates()
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
        mergeReplacements(mailTemplateWithUpdates, foundMailTemplate)
        MailTemplate updatedMailTemplate = mailTemplateService.updateMailTemplate(mailTemplateWithUpdates)
        return mailTemplateConverter.convertMailTemplateToView(updatedMailTemplate)
    }

    void mergeReplacements(MailTemplate toUpdate, MailTemplate existed) {
        Map<String, MailTemplateReplacement> replacementsByKey = existed.mailTemplateReplacements.collectEntries { [(it.key): it] }
        toUpdate.mailTemplateReplacements.each { it.id = replacementsByKey[it.key]?.id }

        List<MailTemplateReplacement> toSave = toUpdate.mailTemplateReplacements.findAll { !it.id }.toList()
        List<MailTemplateReplacement> saved = mailTemplateReplacementService.bulkSave(toSave)

        Map<Integer, MailTemplateReplacement> allNewReplacementsById = toUpdate.mailTemplateReplacements.collectEntries { [(it.id): it] }

        Set<MailTemplateReplacement> toDelete = existed.mailTemplateReplacements.findAll { allNewReplacementsById[it.id] == null }
        mailTemplateReplacementService.bulkDelete(toDelete.toList())
    }

    @Override
    MailTemplateView deleteMailTemplateById(Integer mailTemplateIdForDelete) {
        MailTemplate deletedMailTemplate = mailTemplateService.deleteMailTemplateById(mailTemplateIdForDelete)
        return deletedMailTemplate ? mailTemplateConverter.convertMailTemplateToView(deletedMailTemplate) : null
    }
}
