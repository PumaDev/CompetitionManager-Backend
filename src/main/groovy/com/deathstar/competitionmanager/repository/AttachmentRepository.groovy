package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.Attachment
import org.springframework.data.jpa.repository.JpaRepository

interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    List<Attachment> findAttachmentsByCompetitionId(Integer competitionId)
}