package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItemPair
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridSportsman

class CompetitionCategoryGridPairProcessor {

    CompetitionCategoryGridPairProcessor left
    CompetitionCategoryGridPairProcessor right

    List<RegistratedSportsman> sportsmen = []

    void addSportsman(RegistratedSportsman sportsman) {
        sportsmen << sportsman
    }

    CompetitionCategoryGridPairProcessor current

    private CompetitionCategoryGridPairProcessor next() {
        current = current != null && current == left ? right : left
        return current
    }

    CompetitionCategoryGridItem divide() {
        if (sportsmen.size() <= 1) {
            return new CompetitionCategoryGridSportsman(sportsman: sportsmen[0])
        }

        Map<String, List<RegistratedSportsman>> sportsmenByClub = sportsmen.groupBy { sportsman -> sportsman.clubName }
        left = new CompetitionCategoryGridPairProcessor()
        right = new CompetitionCategoryGridPairProcessor()
        sportsmenByClub.forEach { String clubName, List<RegistratedSportsman> clubSportsmen ->
            clubSportsmen.each {
                next().addSportsman(it)
            }
        }

        return new CompetitionCategoryGridItemPair(left: left.divide(), right: right.divide())
    }

}