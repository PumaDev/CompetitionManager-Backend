package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.domain.RegistrationStatus
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.security.SecurityEndpoint
import com.deathstar.competitionmanager.service.competition.CompetitionViewService
import com.deathstar.competitionmanager.view.CompetitionView
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.InputStreamResource
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static com.deathstar.competitionmanager.domain.user.UserRole.ADMIN
import static com.deathstar.competitionmanager.domain.user.UserRole.DEVELOPER

@RestController
class CompetitionController {

    @Autowired
    CompetitionViewService competitionViewService

    @SecurityEndpoint(rolesHasAccess = [ADMIN])
    @PostMapping('/competition')
    ResponseEntity<CompetitionView> createCompetition(@RequestBody CompetitionView competitionView) {
        CompetitionView createdCompetition = competitionViewService.save(competitionView)
        return new ResponseEntity<CompetitionView>(createdCompetition, HttpStatus.CREATED)
    }

    @SecurityEndpoint()
    @GetMapping('/competition/{id}')
    CompetitionView getCompetitionById(@PathVariable('id') Integer id) {
        CompetitionView competition = competitionViewService.findById(id)
        if (!competition) {
            throw new EntityNotFoundException()
        }
        return competition
    }

    @SecurityEndpoint(rolesHasAccess = [DEVELOPER])
    @GetMapping('/competitions')
    List<CompetitionView> getAllCompetitions() {
        return competitionViewService.getAllCompetitions()
    }

    @SecurityEndpoint()
    @GetMapping('/competitions/future')
    List<CompetitionView> getFutureCompetitions(@RequestParam('page') int page,
                                                @RequestParam(value = 'size') int size) {
        return competitionViewService.findFeatureCompetitions(new PageRequest(page, size)).content
    }

    @SecurityEndpoint()
    @GetMapping('/competitions/last')
    List<CompetitionView> getLastCompetitions(@RequestParam('page') int page,
                                              @RequestParam(value = 'size') int size) {
        return competitionViewService.findLastCompetitions(new PageRequest(page, size)).content
    }

    @SecurityEndpoint(rolesHasAccess = [ADMIN])
    @PutMapping('/competition/{competitionId}/registration/{registrationStatus}')
    ResponseEntity<CompetitionView> updateRegistrationStatusOnCompetition(@PathVariable('competitionId') Integer competitionId,
                                                                          @PathVariable('registrationStatus') RegistrationStatus registrationStatus) {
        return new ResponseEntity<CompetitionView>(competitionViewService.updateRegistrationStatus(competitionId, registrationStatus), HttpStatus.OK)
    }

    @SecurityEndpoint()
    @GetMapping('/competition/{competitionId}/categories')
    List<CompetitionCategoryView> getCategoriesByCompetition(@PathVariable('competitionId') Integer competitionId) {
        return competitionViewService.getCategoriesByCompetitionId(competitionId)
    }

    @SecurityEndpoint(rolesHasAccess = [ADMIN, DEVELOPER])
    @GetMapping('/competition/{competitionId}/grid/generate')
    ResponseEntity<InputStreamResource> generateGrids(@PathVariable('competitionId') Integer competitionId) {
        Tuple2<File, CompetitionView> competitionGrids = competitionViewService.generateGrids(competitionId)

        HttpHeaders httpHeaders = new HttpHeaders()
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM)
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, String.format('attachment; filename="%s.zip"', competitionGrids.first.name))
        httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)

        InputStreamResource fileSystemResource = new InputStreamResource(new FileInputStream(competitionGrids.first))

        return new ResponseEntity<InputStreamResource>(fileSystemResource, httpHeaders, HttpStatus.OK)
    }
}
