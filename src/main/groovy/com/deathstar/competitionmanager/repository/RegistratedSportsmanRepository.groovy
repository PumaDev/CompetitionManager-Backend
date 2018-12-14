package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import org.springframework.data.jpa.repository.JpaRepository

interface RegistratedSportsmanRepository extends JpaRepository<RegistratedSportsman, Integer> {

    List<RegistratedSportsman> findRegistratedSportsmansByCompetitionId(Integer competitionId)

    List<RegistratedSportsman> findRegistratedSportsmansByClubNameAndCompetitionId(String clubName, Integer competitionId)
}