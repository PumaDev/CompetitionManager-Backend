package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.Competition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

import java.time.LocalDate

interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    Page<Competition> findCompetitionsByStartDateAfterOrderByStartDate(LocalDate localDate, Pageable pageable)

    Page<Competition> findCompetitionsByStartDateBeforeOrderByStartDateDesc(LocalDate localDate, Pageable pageable)
}
