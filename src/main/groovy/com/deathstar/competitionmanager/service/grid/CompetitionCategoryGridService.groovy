package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.Competition

interface CompetitionCategoryGridService {

    File generateCategoryGridsForCompetition(Competition competition)
}