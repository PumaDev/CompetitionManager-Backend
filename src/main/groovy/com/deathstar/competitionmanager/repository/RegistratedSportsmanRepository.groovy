package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RegistratedSportsmanRepository extends JpaRepository<RegistratedSportsman, Integer> {

    List<RegistratedSportsman> findRegistratedSportsmansByCompetitionId(Integer competitionId)

    List<RegistratedSportsman> findRegistratedSportsmansByClubNameAndCompetitionId(String clubName, Integer competitionId)

    @Query('select s from RegistratedSportsman s WHERE s.competitionId = :1 and s.categoryId = :2')
    List<RegistratedSportsman> findSportsmenByCompetitionIdAndCategoryId(Integer competitionId, Integer categoryId)
}