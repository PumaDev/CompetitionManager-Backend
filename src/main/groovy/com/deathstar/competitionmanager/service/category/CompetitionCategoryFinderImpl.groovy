package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.domain.RegistratedSportsman
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CompetitionCategoryFinderImpl implements CompetitionCategoryFinder {

    @Autowired
    CompetitionCategoryService competitionCategoryService

    @Override
    CompetitionCategory findCompetitionCategoryForSportsman(RegistratedSportsman sportsman) {
        List<CompetitionCategory> allCategories = competitionCategoryService.readAll()
        List<CompetitionCategory> suitableCategories = allCategories
                .findAll { category -> category.male == sportsman.male }
                .findAll { category -> agePredicate(category, sportsman) }
                .findAll { category -> weightPredicate(category, sportsman) }
                .findAll { category -> experiencePredicate(category, sportsman) }

        return suitableCategories ? suitableCategories[0] : null
    }

    static boolean agePredicate(CompetitionCategory category, RegistratedSportsman sportsman) {
        boolean sportsmanCanBeInCategory = false
        sportsmanCanBeInCategory = category.lowerAge ? category.lowerAge <= sportsman.age : sportsmanCanBeInCategory
        sportsmanCanBeInCategory = category.upperAge ? category.upperAge >= sportsman.age : sportsmanCanBeInCategory
        return sportsmanCanBeInCategory
    }

    static boolean weightPredicate(CompetitionCategory category, RegistratedSportsman sportsman) {
        boolean sportsmanCanBeInCategory = false
        sportsmanCanBeInCategory = category.lowerWeight ? category.lowerWeight <= sportsman.weight : sportsmanCanBeInCategory
        sportsmanCanBeInCategory = category.upperWeight ? category.upperWeight >= sportsman.weight : sportsmanCanBeInCategory
        return sportsmanCanBeInCategory
    }

    static boolean experiencePredicate(CompetitionCategory category, RegistratedSportsman sportsman) {
        boolean sportsmanCanBeInCategory = false
        sportsmanCanBeInCategory = category.lowerExperience ? category.lowerExperience <= sportsman.experience : sportsmanCanBeInCategory
        sportsmanCanBeInCategory = category.upperExperience ? category.upperExperience >= sportsman.experience : sportsmanCanBeInCategory
        return sportsmanCanBeInCategory
    }
}
