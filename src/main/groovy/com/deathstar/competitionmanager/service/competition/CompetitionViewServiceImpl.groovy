package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.view.CompetitionView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CompetitionViewServiceImpl implements CompetitionViewService {

    @Autowired
    CompetitionService competitionService

    @Autowired
    CompetitionConverter competitionConverter

    @Override
    CompetitionView save(CompetitionView competitionView) {
        Competition competition = competitionConverter.convertToDomainEntity(competitionView)
        Competition createdCompetition = competitionService.save(competition)
        return competitionConverter.convertToView(createdCompetition)
    }

    @Override
    CompetitionView findById(Integer id) {
        Competition foundCompetition = competitionService.findById(id)
        return competitionConverter.convertToView(foundCompetition)
    }

    @Override
    Page<CompetitionView> findFeatureCompetitions(Pageable pageable) {
        Page<Competition> featureCompetitions = competitionService.findFeatureCompetitions(pageable)
        return competitionConverter.convertToViews(featureCompetitions, pageable)
    }

    @Override
    Page<CompetitionView> findLastCompetitions(Pageable pageable) {
        Page<Competition> lastCompetitions = competitionService.findLastCompetitions(pageable)
        return competitionConverter.convertToViews(lastCompetitions, pageable)
    }

    @Override
    CompetitionView update(CompetitionView competitionView) {
        Competition competition = competitionConverter.convertToDomainEntity(competitionView)
        Competition updatedCompetition = competitionService.save(competition)
        return competitionConverter.convertToView(updatedCompetition)
    }

    @Override
    List<CompetitionView> getAllCompetitions() {
        return competitionConverter.convertToViews(competitionService.getAllCompetitions())
    }
}
