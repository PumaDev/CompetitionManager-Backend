package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.repository.CompetitionCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompetitionCategoryServiceImpl implements CompetitionCategoryService {

    @Autowired
    CompetitionCategoryRepository competitionCategoryRepository

    @Override
    List<CompetitionCategory> readAll() {
        return competitionCategoryRepository.findAll()
    }

    @Override
    CompetitionCategory create(CompetitionCategory competitionCategory) {
        return competitionCategoryRepository.save(competitionCategory)
    }

    @Override
    List<CompetitionCategory> bulkCreate(List<CompetitionCategory> competitionCategories) {
        return competitionCategoryRepository.save(competitionCategories)
    }

    @Override
    CompetitionCategory update(CompetitionCategory competitionCategory) {
        return competitionCategoryRepository.save(competitionCategory)
    }

    @Override
    List<CompetitionCategory> bulkUpdate(List<CompetitionCategory> competitionCategories) {
        return competitionCategoryRepository.save(competitionCategories)
    }
}
