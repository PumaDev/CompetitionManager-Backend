package com.deathstar.competitionmanager.service.category.age

import com.deathstar.competitionmanager.domain.AgeCategory

interface AgeCategoryService {

    AgeCategory create(AgeCategory ageCategory)

    AgeCategory findById(Integer id)

    List<AgeCategory> readAll()

    AgeCategory update(AgeCategory ageCategory)
}