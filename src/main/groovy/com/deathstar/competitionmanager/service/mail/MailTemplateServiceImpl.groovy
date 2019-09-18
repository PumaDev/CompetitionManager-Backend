package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.MailTemplate
import com.deathstar.competitionmanager.exception.ConflictEntityException
import com.deathstar.competitionmanager.repository.MailTemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MailTemplateServiceImpl implements MailTemplateService {

    @Autowired
    MailTemplateRepository mailTemplateRepository

    @Override
    MailTemplate createMailTemplate(MailTemplate mailTemplate) {
        verifyUniqueFields(mailTemplate)
        return mailTemplateRepository.save(mailTemplate)
    }

    @Override
    MailTemplate updateMailTemplate(MailTemplate mailTemplate) {
        verifyUniqueFields(mailTemplate)
        return mailTemplateRepository.save(mailTemplate)
    }

    @Override
    MailTemplate deleteMailTemplateById(Integer mailTemplateId) {
        MailTemplate mailTemplate = mailTemplateRepository.findOne(mailTemplateId)
        mailTemplateRepository.delete(mailTemplateId as Integer)
        return mailTemplate
    }

    @Override
    MailTemplate findMailTemplateByKeyName(String keyName) {
        return mailTemplateRepository.findMailTemplateByKeyName(keyName)
    }

    @Override
    MailTemplate getMailTemplateById(Integer id) {
        return mailTemplateRepository.findOne(id)
    }

    @Override
    List<MailTemplate> readAllMailTemplates() {
        return mailTemplateRepository.findAll()
    }

    private final void verifyUniqueFields(MailTemplate mailTemplate) {
        MailTemplate foundExistingByKeyNameMailTemplate = mailTemplateRepository.findMailTemplateByKeyName(mailTemplate.keyName)
        if (foundExistingByKeyNameMailTemplate && foundExistingByKeyNameMailTemplate.id != mailTemplate.id) {
            throw new ConflictEntityException("MailTemplate with keyName ${mailTemplate.keyName} already exists")
        }
    }
}
