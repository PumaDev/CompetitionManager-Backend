package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.security.SecurityEndpoint
import com.deathstar.competitionmanager.service.category.CompetitionCategoryViewService
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static com.deathstar.competitionmanager.domain.user.UserRole.ADMIN
import static com.deathstar.competitionmanager.domain.user.UserRole.COACH

@RestController
class CompetitionCategoryController {

    @Autowired
    CompetitionCategoryViewService competitionCategoryViewService

    @SecurityEndpoint(rolesHasAccess = [ADMIN])
    @PostMapping('/competition-category')
    ResponseEntity<CompetitionCategoryView> create(@RequestBody CompetitionCategoryView competitionCategory) {
        CompetitionCategoryView createdCategory = competitionCategoryViewService.create(competitionCategory)
        return new ResponseEntity<CompetitionCategoryView>(createdCategory, HttpStatus.OK)
    }

    @SecurityEndpoint(rolesHasAccess = [ADMIN])
    @PostMapping('/competition-category/bulk')
    ResponseEntity<List<CompetitionCategoryView>> createBulk(@RequestBody List<CompetitionCategoryView> competitionCategories) {
        List<CompetitionCategoryView> createdCategoryViews = competitionCategoryViewService.bulkCreate(competitionCategories)
        return new ResponseEntity<List<CompetitionCategoryView>>(createdCategoryViews, HttpStatus.OK)
    }

    @SecurityEndpoint(rolesHasAccess = [ADMIN])
    @PutMapping('/competition-category')
    ResponseEntity<CompetitionCategoryView> update(@RequestBody CompetitionCategoryView competitionCategory) {
        CompetitionCategoryView updatedCategory = competitionCategoryViewService.update(competitionCategory)
        return new ResponseEntity<CompetitionCategoryView>(updatedCategory, HttpStatus.OK)
    }

    @SecurityEndpoint()
    @GetMapping('/competition-category')
    List<CompetitionCategoryView> readAll() {
        return competitionCategoryViewService.readAll()
    }

    @SecurityEndpoint()
    @GetMapping('/competition-category/{id}')
    CompetitionCategoryView findById(@PathVariable('id') Integer id) {
        return competitionCategoryViewService.findById(id)
    }

    @SecurityEndpoint()
    @GetMapping('/competition-category/sections')
    List<String> getSections() {
        return competitionCategoryViewService.getSections()
    }
}
