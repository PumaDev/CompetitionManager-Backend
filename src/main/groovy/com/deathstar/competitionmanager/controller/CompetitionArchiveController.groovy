package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.service.competition.CompetitionArchiveService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CompetitionArchiveController {

    @Autowired
    CompetitionArchiveService competitionArchiveService

    @GetMapping('/competition-archive/{archiveName}/download')
    ResponseEntity<InputStreamResource> downloadArchive(@PathVariable('archiveName') String archiveName) {
        Tuple2<File, String> archiveInfo = competitionArchiveService.getCompetitionArchive(archiveName)

        HttpHeaders httpHeaders = new HttpHeaders()
        httpHeaders.setContentType(MediaType.APPLICATION_PDF)
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, String.format('attachment; filename="%s"', archiveInfo.second))
        httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)

        InputStreamResource fileSystemResource = new InputStreamResource(new FileInputStream(archiveInfo.first))

        return new ResponseEntity<InputStreamResource>(fileSystemResource, httpHeaders, HttpStatus.OK)
    }
}
