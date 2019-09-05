package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.repository.CompetitionCategoryRepository
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompetitionCategoryServiceImpl implements CompetitionCategoryService {

    @Autowired
    CompetitionCategoryRepository competitionCategoryRepository

    @Override
    List<CompetitionCategory> readAll() {
        return competitionCategoryRepository.findAllByOrderByDisplayNameAsc()
    }

    @Override
    CompetitionCategory findById(Integer id) {
        return competitionCategoryRepository.findOne(id)
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

    @Override
    List<String> getSections() {
        return competitionCategoryRepository.findDistinctSections()
    }

    @Override
    List<CompetitionCategory> findByIds(List<Integer> ids) {
        return competitionCategoryRepository.findAll(ids)
    }
}
