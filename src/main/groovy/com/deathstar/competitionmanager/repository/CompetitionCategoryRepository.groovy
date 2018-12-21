package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CompetitionCategoryRepository extends JpaRepository<CompetitionCategory, Integer> {

    List<CompetitionCategory> findAllByOrderByDisplayNameAsc()

    @Query('SELECT DISTINCT c.section from CompetitionCategory c')
    List<String> findDistinctSections()
}
