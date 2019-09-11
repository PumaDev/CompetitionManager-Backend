package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.service.attachment.AttachmentViewService
import com.deathstar.competitionmanager.view.StatusView
import com.deathstar.competitionmanager.view.attachment.AttachmentView
import com.deathstar.competitionmanager.view.attachment.CreateAttachmentView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class AttachmentController {

    @Autowired
    AttachmentViewService attachmentViewService

    @PostMapping("/v1/competitions/{competitionId}/attachments")
    AttachmentView createAttachment(@PathVariable('competitionId') Integer competitionId,
                                    @RequestParam("name") String name,
                                    MultipartFile attachmentContent) {
        CreateAttachmentView createAttachmentView = new CreateAttachmentView(competitionId: competitionId, attachmentName: name)
        attachmentViewService.createAttachment(createAttachmentView, attachmentContent)
    }

    @GetMapping('/v1/competitions/{competitionId}/attachments')
    List<AttachmentView> findAttachmentsByCompetition(@PathVariable('competitionId') Integer competitionId) {
        return attachmentViewService.findAttachmentsByCompetitionId(competitionId)
    }

    @GetMapping('/v1/competitions/{competitionId}/attachments/{attachmentId}/content')
    ResponseEntity<InputStreamResource> getAttachmentContent(@PathVariable('attachmentId') Integer attachmentId) {
        Tuple2<InputStream, String> attachment = attachmentViewService.getAttachmentContentById(attachmentId)

        HttpHeaders httpHeaders = new HttpHeaders()
        httpHeaders.setContentType(MediaType.APPLICATION_PDF)
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, String.format('attachment; filename="%s"', attachment.second))
        httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)

        InputStreamResource content = new InputStreamResource(attachment.first)

        return new ResponseEntity<InputStreamResource>(content, httpHeaders, HttpStatus.OK)
    }

    @DeleteMapping('/v1/competitions/{competitionId}/attachments/{attachmentId}')
    StatusView deleteAttachmentById(@PathVariable('attachmentId') Integer attachmentId) {
        attachmentViewService.deleteAttachmentById(attachmentId)
        return new StatusView(status: "DELETED")
    }
}
