package com.deathstar.competitionmanager.service.sportsman

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.exception.AccessDeniedException
import com.deathstar.competitionmanager.exception.RegistrationClosedException
import com.deathstar.competitionmanager.security.CurrentUserResolver
import com.deathstar.competitionmanager.service.category.CompetitionCategoryFinder
import com.deathstar.competitionmanager.service.competition.CompetitionService
import com.deathstar.competitionmanager.view.sportsman.CreateRegistratedSportsmanView
import com.deathstar.competitionmanager.view.sportsman.RegistratedSportsmanView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegistratedSportsmanViewServiceImpl implements RegistratedSportsmanViewService {

    @Autowired
    RegistratedSportsmanService registratedSportsmanService

    @Autowired
    RegistratedSportsmanConverter registratedSportsmanConverter

    @Autowired
    CurrentUserResolver currentUserResolver

    @Autowired
    CompetitionService competitionService

    @Autowired
    CompetitionCategoryFinder competitionCategoryFinder

    @Override
    RegistratedSportsmanView register(Integer competitionId, CreateRegistratedSportsmanView createRegistratedSportsmanView) {
        Competition competition = competitionService.findById(competitionId)
        if (!competition || !competition.registrationStatus.canRegister) {
            throw new RegistrationClosedException(competitionId)
        }
        RegistratedSportsman registratedSportsman = registratedSportsmanConverter.convertToDto(createRegistratedSportsmanView)
        CompetitionCategory suitableCategory = competitionCategoryFinder.findCompetitionCategoryForSportsman(createRegistratedSportsmanView)
        if (!suitableCategory) {
            throw new NotFoundSuitableCategoryException()
        }
        registratedSportsman.competitionCategory = suitableCategory
        registratedSportsman.competitionId = competitionId
        registratedSportsman.clubName = currentUserResolver.getCurrentUser().clubName
        RegistratedSportsman createdRegistratedSportsman = registratedSportsmanService.create(registratedSportsman)
        return registratedSportsmanConverter.convertToView(createdRegistratedSportsman)
    }

    @Override
    List<RegistratedSportsmanView> getSportsmenByClubAndCompetitionId(String clubName, Integer competitionId) {
        List<RegistratedSportsman> sportsmen = registratedSportsmanService.findSportsmenByClubNameAndCompetitionId(clubName, competitionId)
        return registratedSportsmanConverter.convertToViews(sportsmen)
    }

    @Override
    List<RegistratedSportsmanView> getSportsmanByCompetitionId(Integer competitionId) {
        List<RegistratedSportsman> sportsmen = registratedSportsmanService.findSportsmanByCompetitionId(competitionId)
        return registratedSportsmanConverter.convertToViews(sportsmen)
    }

    @Override
    void deleteRegistratedSportsman(Integer sportsmanId) {
        RegistratedSportsman sportsman = registratedSportsmanService.getById(sportsmanId)
        if (!sportsman || sportsman.clubName != currentUserResolver.getCurrentUser().clubName) {
            throw new AccessDeniedException('You try remove sportsman not from your club')
        }
        registratedSportsmanService.delete(sportsmanId)
    }
}
