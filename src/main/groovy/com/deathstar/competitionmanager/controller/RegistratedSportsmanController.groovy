package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.security.CurrentUserResolver
import com.deathstar.competitionmanager.security.SecurityEndpoint
import com.deathstar.competitionmanager.service.sportsman.RegistratedSportsmanViewService
import com.deathstar.competitionmanager.view.sportsman.CreateRegistratedSportsmanView
import com.deathstar.competitionmanager.view.sportsman.RegistratedSportsmanView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static com.deathstar.competitionmanager.domain.user.UserRole.*
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@RestController
class RegistratedSportsmanController {

    @Autowired
    RegistratedSportsmanViewService registratedSportsmanViewService

    @Autowired
    CurrentUserResolver currentUserResolver

    @SecurityEndpoint(rolesHasAccess = [COACH])
    @PostMapping('/sportsman/register/{competitionId}')
    ResponseEntity<RegistratedSportsmanView> registerSportsmanForCompetition(@PathVariable('competitionId') Integer competitionId,
                                                             @RequestBody CreateRegistratedSportsmanView createRegistratedSportsmanView) {
        RegistratedSportsmanView createdSportsman = registratedSportsmanViewService.register(competitionId, createRegistratedSportsmanView)
        return new ResponseEntity<RegistratedSportsmanView>(createdSportsman, CREATED)
    }

    @SecurityEndpoint(rolesHasAccess = [COACH, ADMIN, DEVELOPER])
    @GetMapping('/sportsman/find/by-competition/{competitionId}')
    List<RegistratedSportsmanView> findRegistratedSportsmanByCOmpetitionForCurrentUsert(@PathVariable('competitionId') Integer competitionId) {
        User currentUser = currentUserResolver.getCurrentUser()
        if (currentUser.userRole == COACH) {
            return registratedSportsmanViewService.getSportsmenByClubAndCompetitionId(currentUser.clubName, competitionId)
        } else {
            return registratedSportsmanViewService.getSportsmanByCompetitionId(competitionId)
        }
    }

    @SecurityEndpoint(rolesHasAccess = [COACH, ADMIN])
    @DeleteMapping('/sportsman/delete/{sportsmanId}')
    ResponseEntity<Void> deleteFromRegistration(@PathVariable('sportsmanId') Integer sportsmanId) {
        registratedSportsmanViewService.deleteRegistratedSportsman(sportsmanId)
        return new ResponseEntity<Void>(OK)
    }
}
