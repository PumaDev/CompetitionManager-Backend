package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.RegistrationStatus
import com.deathstar.competitionmanager.view.CompetitionView
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CompetitionViewService {

    CompetitionView save(CompetitionView competition)

    CompetitionView findById(Integer id)

    Page<CompetitionView> findFeatureCompetitions(Pageable pageable)

    Page<CompetitionView> findLastCompetitions(Pageable pageable)

    CompetitionView update(CompetitionView competition)

    CompetitionView updateRegistrationStatus(Integer competitionId, RegistrationStatus newRegistrationStatus)

    List<CompetitionView> getAllCompetitions()

    List<CompetitionCategoryView> getCategoriesByCompetitionId(Integer competitionId)

    void generateGrids(Integer competitionId)
}
