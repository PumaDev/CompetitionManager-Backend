package com.deathstar.competitionmanager.service.category


import com.deathstar.competitionmanager.view.category.CompetitionCategoryView

interface CompetitionCategoryViewService {

    List<CompetitionCategoryView> readAll()

    CompetitionCategoryView create(CompetitionCategoryView competitionCategory)

    List<CompetitionCategoryView> bulkCreate(List<CompetitionCategoryView> competitionCategories)

    CompetitionCategoryView update(CompetitionCategoryView competitionCategory)

    List<CompetitionCategoryView> bulkUpdate(List<CompetitionCategoryView> competitionCategories)

    CompetitionCategoryView findById(Integer id)
}