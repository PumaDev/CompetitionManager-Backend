package com.deathstar.competitionmanager.service.sportsman

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import io.swagger.models.auth.In

interface RegistratedSportsmanService {

    RegistratedSportsman create(RegistratedSportsman sportsman)

    RegistratedSportsman getById(Integer id)

    List<RegistratedSportsman> readAll()

    List<RegistratedSportsman> findSportsmanByCompetitionId(Integer competitionId)

    List<RegistratedSportsman> findSportsmanByClubName(String clubName)

    List<RegistratedSportsman> bulkUpdate(List<RegistratedSportsman> sportsman)

    List<RegistratedSportsman> findSportsmenByClubNameAndCompetitionId(String clubName, Integer competitionId)

    List<RegistratedSportsman> findSportsmenByCompetitionIdAndCategoryId(Integer competitionId, Integer categoryId)

    RegistratedSportsman delete(Integer sportsmanId)
}