package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CompetitionService {

    Competition save(Competition competition)

    Competition findById(Integer id)

    Page<Competition> findFeatureCompetitions(Pageable pageable)

    Page<Competition> findLastCompetitions(Pageable pageable)

    Competition update(Competition competition)

    boolean deleteCompetitionById(Integer id)

    List<Competition> getAllCompetitions()
}