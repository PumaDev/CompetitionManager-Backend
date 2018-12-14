package com.deathstar.competitionmanager.service.sportsman

import com.deathstar.competitionmanager.domain.RegistratedSportsman

interface RegistratedSportsmanService {

    RegistratedSportsman create(RegistratedSportsman sportsman)

    RegistratedSportsman getById(Integer id)

    List<RegistratedSportsman> readAll()

    List<RegistratedSportsman> findSportsmanByCompetitionId(Integer competitionId)

    List<RegistratedSportsman> findSportsmenByClubNameAndCompetitionId(String clubName, Integer competitionId)

    RegistratedSportsman delete(Integer sportsmanId)
}