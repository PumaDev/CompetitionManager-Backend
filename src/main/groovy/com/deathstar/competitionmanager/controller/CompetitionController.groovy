package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.domain.RegistrationStatus
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.security.SecurityEndpoint
import com.deathstar.competitionmanager.service.competition.CompetitionViewService
import com.deathstar.competitionmanager.view.CompetitionView
import javafx.scene.shape.Path
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static com.deathstar.competitionmanager.domain.RegistrationStatus.CLOSED
import static com.deathstar.competitionmanager.domain.RegistrationStatus.OPEN
import static com.deathstar.competitionmanager.domain.RegistrationStatus.REOPEN
import static com.deathstar.competitionmanager.domain.user.UserRole.*

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

    @SecurityEndpoint(rolesHasAccess = [COACH, ADMIN, DEVELOPER])
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

    @SecurityEndpoint(rolesHasAccess = [COACH, ADMIN, DEVELOPER])
    @GetMapping('/competitions/future')
    List<CompetitionView> getFutureCompetitions(@RequestParam('page') int page,
                                                @RequestParam(value = 'size') int size) {
        return competitionViewService.findFeatureCompetitions(new PageRequest(page, size)).content
    }

    @SecurityEndpoint(rolesHasAccess = [COACH, ADMIN, DEVELOPER])
    @GetMapping('/competitions/last')
    List<CompetitionView> getLastCompetitions(@RequestParam('page') int page,
                                              @RequestParam(value = 'size') int size) {
        return competitionViewService.findLastCompetitions(new PageRequest(page, size)).content
    }

    @SecurityEndpoint(rolesHasAccess = [ADMIN])
    @PostMapping('/competition/{competitionId}/registration/{registrationStatus}')
    ResponseEntity<Void> updateRegistrationStatusOnCompetition(@PathVariable('competitionId') Integer competitionId,
                                                               @PathVariable('registrationStatus') RegistrationStatus registrationStatus) {
        competitionViewService.updateRegistrationStatus(competitionId, registrationStatus)
        return new ResponseEntity<Void>(HttpStatus.OK)
    }
}
