package com.deathstar.competitionmanager.service.attachment

import com.deathstar.competitionmanager.domain.Attachment
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.service.storage.StorageService
import com.deathstar.competitionmanager.view.attachment.AttachmentView
import com.deathstar.competitionmanager.view.attachment.CreateAttachmentView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class AttachmentViewServiceImpl implements AttachmentViewService {

    @Autowired
    AttachmentService attachmentService

    @Autowired
    StorageService storageService

    @Autowired
    AttachmentConverter attachmentConverter

    @Value('${cm.storage.attachment-type:attachment}')
    String attachmentStorageType

    @Transactional
    @Override
    AttachmentView createAttachment(CreateAttachmentView createAttachmentView, MultipartFile attachmentFile) {
        String path = UUID.randomUUID().toString()

        Attachment attachment = attachmentConverter.convertCreateViewToAttachment(createAttachmentView)
        attachment.contentLink = path
        attachment.fileName = attachmentFile.originalFilename

        Attachment createdAttachment = attachmentService.createAttachment(attachment)
        storageService.saveContent(attachmentStorageType, attachmentFile.inputStream, path)

        attachmentConverter.convertAttachmentToView(createdAttachment)
    }

    @Override
    List<AttachmentView> findAttachmentsByCompetitionId(Integer competitionId) {
        return attachmentService
                .findAttachmentsByCompetitionId(competitionId)
                .collect { attachment -> attachmentConverter.convertAttachmentToView(attachment) }
    }

    @Transactional
    @Override
    void deleteAttachmentById(Integer attachmentId) {
        Attachment attachment = attachmentService.getAttachmentById(attachmentId)
        if (attachment) {
            attachmentService.deleteAttachmentById(attachmentId)
            storageService.deleteContent(attachmentStorageType, attachment.contentLink)
        }
    }

    @Override
    Tuple2<InputStream, String> getAttachmentContentById(Integer attachmentId) {
        Attachment attachment = attachmentService.getAttachmentById(attachmentId)
        if (!attachment) {
            throw new EntityNotFoundException("Attachment by id ${attachmentId} was not found")
        }

        new Tuple2<>(storageService.getContent(attachmentStorageType, attachment.contentLink), attachment.fileName)
    }
}
