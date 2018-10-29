package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.WeightCategory
import org.springframework.data.jpa.repository.JpaRepository

interface WeightCategoryRepository extends JpaRepository<WeightCategory, Integer> {
}
