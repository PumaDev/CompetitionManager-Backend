package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RegistratedSportsmanRepository extends JpaRepository<RegistratedSportsman, Integer> {

    List<RegistratedSportsman> findRegistratedSportsmansByCompetitionId(Integer competitionId)

    List<RegistratedSportsman> findRegistratedSportsmansByClubNameAndCompetitionId(String clubName, Integer competitionId)

    List<RegistratedSportsman> findRegistratedSportsmansByClubName(String clubName)

    @Query( nativeQuery = true, value = 'select * from registrated_sportsman s WHERE s.competition_id = :competitionId and s.competition_category_id = :categoryId')
    List<RegistratedSportsman> findSportsmenByCompetitionIdAndCategoryId(@Param('competitionId') Integer competitionId, @Param('categoryId') Integer categoryId)
}