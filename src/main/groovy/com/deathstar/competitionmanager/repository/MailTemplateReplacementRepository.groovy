package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.mail.MailTemplateReplacement
import org.springframework.data.jpa.repository.JpaRepository

interface MailTemplateReplacementRepository extends JpaRepository<MailTemplateReplacement, Integer> {

}