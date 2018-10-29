package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.AgeCategory
import org.springframework.data.jpa.repository.JpaRepository

interface AgeCategoryRepository extends JpaRepository<AgeCategory, Integer> {

    AgeCategory findCategoryByDisplayName(String name)
}