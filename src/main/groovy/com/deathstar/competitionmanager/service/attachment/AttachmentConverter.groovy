package com.deathstar.competitionmanager.service.attachment

import com.deathstar.competitionmanager.domain.Attachment
import com.deathstar.competitionmanager.view.attachment.AttachmentView
import com.deathstar.competitionmanager.view.attachment.CreateAttachmentView
import org.springframework.stereotype.Component

@Component
class AttachmentConverter {

    AttachmentView convertAttachmentToView(Attachment attachment) {
        return new AttachmentView(
                id: attachment.id,
                competitionId: attachment.competitionId,
                name: attachment.name,
                fileName: attachment.fileName)
    }

    Attachment convertCreateViewToAttachment(CreateAttachmentView createAttachmentView) {
        return new Attachment(
                name: createAttachmentView.attachmentName,
                competitionId: createAttachmentView.competitionId)
    }
}
