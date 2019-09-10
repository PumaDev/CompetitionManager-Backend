package com.deathstar.competitionmanager.service.attachment

import com.deathstar.competitionmanager.domain.Attachment
import com.deathstar.competitionmanager.repository.AttachmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository

    @Override
    Attachment createAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment)
    }

    @Override
    List<Attachment> findAttachmentsByCompetitionId(Integer competitionId) {
        return attachmentRepository.findAttachmentsByCompetitionId(competitionId)
    }

    @Override
    Attachment getAttachmentById(Integer attachmentId) {
        return attachmentRepository.getOne(attachmentId)
    }

    @Override
    void deleteAttachmentById(Integer id) {
        attachmentRepository.delete(id)
    }
}
