package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.domain.RegistratedSportsman

interface CompetitionCategoryFinder {

    CompetitionCategory findCompetitionCategoryForSportsman(RegistratedSportsman sportsman)
}