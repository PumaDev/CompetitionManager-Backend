package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory

interface CompetitionCategoryService {

    List<CompetitionCategory> readAll()

    CompetitionCategory findById(Integer id)

    CompetitionCategory create(CompetitionCategory competitionCategory)

    List<CompetitionCategory> bulkCreate(List<CompetitionCategory> competitionCategories)

    CompetitionCategory update(CompetitionCategory competitionCategory)

    List<CompetitionCategory> bulkUpdate(List<CompetitionCategory> competitionCategories)

    List<String> getSections()
}