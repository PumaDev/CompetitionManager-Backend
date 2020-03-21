package com.deathstar.competitionmanager.service.sportsman

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.service.category.CompetitionCategoryConverter
import com.deathstar.competitionmanager.service.competition.CompetitionConverter
import com.deathstar.competitionmanager.view.sportsman.CreateRegistratedSportsmanView
import com.deathstar.competitionmanager.view.sportsman.RegistratedSportsmanView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RegistratedSportsmanConverter {

    @Autowired
    CompetitionCategoryConverter competitionCategoryConverter

    RegistratedSportsmanView convertToView(RegistratedSportsman sportsman) {
        return new RegistratedSportsmanView(
                id: sportsman.id,
                name: sportsman.name,
                lastName: sportsman.lastName,
                age: sportsman.age,
                experience: sportsman.experience,
                weight: sportsman.weight,
                clubName: sportsman.clubName,
                male: sportsman.male,
                coach: sportsman.coach,
                competitionCategory: competitionCategoryConverter.convertToView(sportsman.competitionCategory))
    }

    RegistratedSportsman convertToDto(CreateRegistratedSportsmanView createView) {
        return new RegistratedSportsman(
                name: createView.name,
                lastName: createView.lastName,
                age: createView.age,
                weight: createView.weight,
                male: createView.male,
                experience: createView.experience,
                coach: createView.coach,
                section: createView.section,
                competitionId: createView.competitionId)
    }

    List<RegistratedSportsmanView> convertToViews(List<RegistratedSportsman> dtos) {
        return dtos.collect { convertToView(it) }
    }
}
