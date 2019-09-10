package com.deathstar.competitionmanager.service.attachment

import com.deathstar.competitionmanager.domain.Attachment

interface AttachmentService {

    Attachment createAttachment(Attachment attachment)

    List<Attachment> findAttachmentsByCompetitionId(Integer competitionId)

    Attachment getAttachmentById(Integer attachmentId)

    void deleteAttachmentById(Integer id)
}