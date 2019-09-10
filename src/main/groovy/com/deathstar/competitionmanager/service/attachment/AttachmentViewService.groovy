package com.deathstar.competitionmanager.service.attachment


import com.deathstar.competitionmanager.view.AttachmentView
import org.springframework.web.multipart.MultipartFile

interface AttachmentViewService {

    AttachmentView createAttachment(Integer competitionId, MultipartFile attachment)

    List<AttachmentView> findAttachmentsByCompetitionId(Integer competitionId)

    void deleteCompetitionById(Integer attachmentId)

    InputStream getAttachmentContentById(Integer attachmentId)
}