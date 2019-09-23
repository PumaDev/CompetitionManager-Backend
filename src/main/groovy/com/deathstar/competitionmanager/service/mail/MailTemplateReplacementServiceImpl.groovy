package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.MailTemplateReplacement
import com.deathstar.competitionmanager.repository.MailTemplateReplacementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MailTemplateReplacementServiceImpl implements MailTemplateReplacementService {

    @Autowired
    MailTemplateReplacementRepository mailTemplateReplacementRepository

    @Override
    List<MailTemplateReplacement> bulkSave(List<MailTemplateReplacement> replacements) {
        return mailTemplateReplacementRepository.save(replacements)
    }

    @Override
    void bulkDelete(List<MailTemplateReplacement> replacements) {
        mailTemplateReplacementRepository.deleteInBatch(replacements)
    }
}
