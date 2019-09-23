package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.mail.MailTemplateReplacement

interface MailTemplateReplacementService {

    List<MailTemplateReplacement> bulkSave(List<MailTemplateReplacement> replacements)

    void bulkDelete(List<MailTemplateReplacement> mailTemplateReplacements)
}