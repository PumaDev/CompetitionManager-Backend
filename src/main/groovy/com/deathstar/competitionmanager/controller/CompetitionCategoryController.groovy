package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.service.category.CompetitionCategoryViewService
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompetitionCategoryController {

    @Autowired
    CompetitionCategoryViewService competitionCategoryViewService

    @PostMapping('/competition-category')
    ResponseEntity<CompetitionCategoryView> create(@RequestBody CompetitionCategoryView competitionCategory) {
        CompetitionCategoryView createdCategory = competitionCategoryViewService.create(competitionCategory)
        return new ResponseEntity<CompetitionCategoryView>(createdCategory, HttpStatus.OK)
    }

    @PostMapping('/competition-category/bulk')
    ResponseEntity<List<CompetitionCategoryView>> createBulk(@RequestBody List<CompetitionCategoryView> competitionCategories) {
        List<CompetitionCategoryView> createdCategoryViews = competitionCategoryViewService.bulkCreate(competitionCategories)
        return new ResponseEntity<List<CompetitionCategoryView>>(createdCategoryViews, HttpStatus.OK)
    }

    @GetMapping('/competition-category')
    List<CompetitionCategoryView> readAll() {
        return competitionCategoryViewService.readAll()
    }
}
