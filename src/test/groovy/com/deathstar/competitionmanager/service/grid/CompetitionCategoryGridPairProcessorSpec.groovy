package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItemPair
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridSportsman
import spock.lang.Specification

class CompetitionCategoryGridPairProcessorSpec extends Specification {

    def 'Should work'() {
        given:
        List<RegistratedSportsman> registratedSportsmen = [
                new RegistratedSportsman(id: 1, clubName: 'a'),
                new RegistratedSportsman(id: 2, clubName: 'b'),
                new RegistratedSportsman(id: 3, clubName: 'a'),
                new RegistratedSportsman(id: 4, clubName: 'b'),
                new RegistratedSportsman(id: 5, clubName: 'c'),
                new RegistratedSportsman(id: 6, clubName: 'd'),
        ]

        when:
        CompetitionCategoryGridPairProcessor processor = new CompetitionCategoryGridPairProcessor(sportsmen: registratedSportsmen)
        CompetitionCategoryGridItem root = processor.divide()

        then:
        root.class == CompetitionCategoryGridItemPair
        def pair = ((CompetitionCategoryGridItemPair) root)
        countOfClubSportsmen(pair.left, 'a') == 1
        countOfClubSportsmen(pair.right, 'a') == 1
        countOfClubSportsmen(pair.left, 'b') == 1
        countOfClubSportsmen(pair.right, 'b') == 1
        countOfClubSportsmen(pair.left, 'c') == 1
        countOfClubSportsmen(pair.right, 'c') == 0
        countOfClubSportsmen(pair.left, 'd') == 0
        countOfClubSportsmen(pair.right, 'd') == 1
    }

    int countOfClubSportsmen(CompetitionCategoryGridItem item, String clubName) {
        if (item.class == CompetitionCategoryGridItemPair) {
            def pair = (CompetitionCategoryGridItemPair) item
            return countOfClubSportsmen(pair.left, clubName) + countOfClubSportsmen(pair.right, clubName)
        } else if (item.class == CompetitionCategoryGridSportsman) {
            def sp = (CompetitionCategoryGridSportsman) item
            return sp.sportsman.clubName == clubName ? 1 : 0
        } else {
            return 0
        }
    }
}
