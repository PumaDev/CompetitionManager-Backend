package com.deathstar.competitionmanager.service.sportsman

import com.deathstar.competitionmanager.view.sportsman.CreateRegistratedSportsmanView
import com.deathstar.competitionmanager.view.sportsman.RegistratedSportsmanView

interface RegistratedSportsmanViewService {

    RegistratedSportsmanView register(Integer competitionId, CreateRegistratedSportsmanView createRegistratedSportsmanView)

    List<RegistratedSportsmanView> getSportsmenByClubAndCompetitionId(String clubName, Integer competitionId)

    List<RegistratedSportsmanView> getSportsmanByCompetitionId(Integer competitionId)

    void deleteRegistratedSportsman(Integer sportsmanId)
}