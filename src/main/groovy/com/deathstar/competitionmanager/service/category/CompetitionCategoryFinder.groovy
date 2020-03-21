package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.view.sportsman.CreateRegistratedSportsmanView

interface CompetitionCategoryFinder {

    CompetitionCategory findCompetitionCategoryForSportsman(RegistratedSportsman sportsman)
}