package com.deathstar.competitionmanager.service.category.age

import com.deathstar.competitionmanager.domain.AgeCategory
import com.deathstar.competitionmanager.repository.AgeCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AgeCategoryServiceImpl implements AgeCategoryService {

    @Autowired
    AgeCategoryRepository ageCategoryRepository

    @Override
    AgeCategory create(AgeCategory ageCategory) {
        AgeCategory foundAgeCategoryByName = ageCategoryRepository.find
        return ageCategoryRepository.save(ageCategory)
    }

    @Override
    AgeCategory findById(Integer id) {
        return null
    }

    @Override
    List<AgeCategory> readAll() {
        return null
    }

    @Override
    AgeCategory update(AgeCategory ageCategory) {
        return null
    }
}
