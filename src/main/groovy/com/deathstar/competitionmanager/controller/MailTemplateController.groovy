package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.domain.user.UserRole
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.security.SecurityEndpoint
import com.deathstar.competitionmanager.service.mail.MailTemplateViewService
import com.deathstar.competitionmanager.view.mail.MailTemplateView
import com.deathstar.competitionmanager.view.mail.UpdateTemplateInMailTemplateView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MailTemplateController {

    @Autowired
    MailTemplateViewService mailTemplateViewService

    @SecurityEndpoint(rolesHasAccess = [UserRole.DEVELOPER])
    @PostMapping('/v1/mail-templates')
    ResponseEntity<MailTemplateView> createMailTemplate(@RequestBody MailTemplateView newMailTemplateView) {
        MailTemplateView createdMailTemplate = mailTemplateViewService.createMailTemplate(newMailTemplateView)
        return new ResponseEntity<MailTemplateView>(createdMailTemplate, HttpStatus.CREATED)
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.DEVELOPER, UserRole.ADMIN])
    @GetMapping('/v1/mail-templates')
    List<MailTemplateView> getAllMailTemplates() {
        return mailTemplateViewService.readAllMailTemplates()
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.DEVELOPER, UserRole.ADMIN])
    @GetMapping('/v1/mail-templates/{mailTemplateId}')
    MailTemplateView getMailTemplateById(@PathVariable('mailTemplateId') Integer mailTemplateId) {
        MailTemplateView foundMailTemplate = mailTemplateViewService.findMailTemplateById(mailTemplateId)
        if (!foundMailTemplate) {
            throw new EntityNotFoundException()
        }
        return foundMailTemplate
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.DEVELOPER])
    @PutMapping('/v1/mail-templates/{mailTemplateId}')
    MailTemplateView updateMailTemplate(@PathVariable('mailTemplateId') Integer mailTemplateId,
                                        @RequestBody MailTemplateView mailTemplateViewWithUpdates) {
        mailTemplateViewWithUpdates.id = mailTemplateId
        return mailTemplateViewService.updateMailTemplate(mailTemplateViewWithUpdates)
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.DEVELOPER, UserRole.ADMIN])
    @PutMapping('/v1/mail-templates/{mailTemplateId}/template')
    MailTemplateView updateTemplateInMailTemplate(@PathVariable('mailTemplateId') Integer mailTemplateId,
                                        @RequestBody UpdateTemplateInMailTemplateView updateTemplateInMailTemplateView) {
        return mailTemplateViewService.updateTemplateInMailTemplate(mailTemplateId, updateTemplateInMailTemplateView)
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.DEVELOPER])
    @DeleteMapping('/v1/mail-templates/{mailTemplateId}')
    MailTemplateView deleteMailTemplate(@PathVariable('mailTemplateId') Integer mailTemplateId) {
        return mailTemplateViewService.deleteMailTemplateById(mailTemplateId)
    }
}
