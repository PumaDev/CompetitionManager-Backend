package com.deathstar.competitionmanager.service.attachment


import com.deathstar.competitionmanager.view.attachment.AttachmentView
import com.deathstar.competitionmanager.view.attachment.CreateAttachmentView
import org.springframework.web.multipart.MultipartFile

interface AttachmentViewService {

    AttachmentView createAttachment(CreateAttachmentView createAttachmentView, MultipartFile attachment)

    List<AttachmentView> findAttachmentsByCompetitionId(Integer competitionId)

    void deleteAttachmentById(Integer attachmentId)

    Tuple2<InputStream, String> getAttachmentContentById(Integer attachmentId)
}