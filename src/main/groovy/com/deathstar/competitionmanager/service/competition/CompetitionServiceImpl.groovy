package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.RegistrationStatus
import com.deathstar.competitionmanager.repository.CompetitionRepository
import groovy.util.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import java.time.LocalDate

@Service
@Log
class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    CompetitionRepository competitionRepository

    @Override
    Competition save(Competition competition) {
        // set default values
        competition.registrationStatus = RegistrationStatus.OPEN
        competition.endDate ?: competition.startDate

        log.info("Create competition ${competition.name}}")

        return competitionRepository.save(competition)
    }

    @Override
    Competition findById(Integer id) {
        return competitionRepository.findOne(id)
    }

    @Override
    Page<Competition> findFeatureCompetitions(Pageable pageable) {
        LocalDate now = LocalDate.now()
        return competitionRepository.findCompetitionsByStartDateAfter(now.minusDays(1), pageable)
    }

    @Override
    Page<Competition> findLastCompetitions(Pageable pageable) {
        LocalDate now = LocalDate.now()
        return competitionRepository.findCompetitionsByStartDateBefore(now.plusDays(1), pageable)
    }

    @Override
    Competition update(Competition competition) {
        Competition existingCompetition = competitionRepository.findOne(competition.id)
        if (!existingCompetition) {
            throw new RuntimeException('Competition not found')
        }

        // patch update
        competition.categories = competition.categories ?: existingCompetition.categories

        return competitionRepository.save(competition)
    }

    @Override
    List<Competition> getAllCompetitions() {
        return competitionRepository.findAll()
    }
}
