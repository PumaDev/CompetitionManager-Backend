package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.mail.MailTemplate
import org.springframework.data.jpa.repository.JpaRepository

interface MailTemplateRepository extends JpaRepository<MailTemplate, Integer> {

    MailTemplate findMailTemplateByKeyName(String keyName)
}
