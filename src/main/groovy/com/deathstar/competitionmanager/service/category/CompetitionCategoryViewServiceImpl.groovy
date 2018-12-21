package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompetitionCategoryViewServiceImpl implements CompetitionCategoryViewService {

    @Autowired
    CompetitionCategoryService competitionCategoryService

    @Autowired
    CompetitionCategoryConverter competitionCategoryConverter

    @Override
    List<CompetitionCategoryView> readAll() {
        return competitionCategoryConverter.convertToViews(competitionCategoryService.readAll())
    }

    @Override
    CompetitionCategoryView create(CompetitionCategoryView competitionCategoryView) {
        CompetitionCategory competitionCategory = competitionCategoryConverter.convertToDomainEntity(competitionCategoryView)
        return competitionCategoryConverter.convertToView(competitionCategoryService.create(competitionCategory))
    }

    @Override
    List<CompetitionCategoryView> bulkCreate(List<CompetitionCategoryView> competitionCategoryViews) {
        List<CompetitionCategory> competitionCategories = competitionCategoryConverter.convertToDomainEntities(competitionCategoryViews)
        return competitionCategoryConverter.convertToViews(competitionCategoryService.bulkCreate(competitionCategories))
    }

    @Override
    CompetitionCategoryView update(CompetitionCategoryView competitionCategoryView) {
        CompetitionCategory competitionCategory = competitionCategoryConverter.convertToDomainEntity(competitionCategoryView)
        return competitionCategoryConverter.convertToView(competitionCategoryService.update(competitionCategory))
    }

    @Override
    List<CompetitionCategoryView> bulkUpdate(List<CompetitionCategoryView> competitionCategoryViews) {
        List<CompetitionCategory> competitionCategories = competitionCategoryConverter.convertToDomainEntities(competitionCategoryViews)
        return competitionCategoryConverter.convertToViews(competitionCategoryService.bulkUpdate(competitionCategories))
    }

    @Override
    CompetitionCategoryView findById(Integer id) {
        CompetitionCategory competitionCategory = competitionCategoryService.findById(id)
        if (!competitionCategory) {
            throw new EntityNotFoundException()
        }
        return competitionCategoryConverter.convertToView(competitionCategory)
    }

    @Override
    List<String> getSections() {
        return competitionCategoryService.getSections()
    }
}
