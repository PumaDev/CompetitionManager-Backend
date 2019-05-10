package com.deathstar.competitionmanager.service.grid.draw

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem

interface GridDrawer {
    File drawGridToFile(CompetitionCategory competitionCategory, CompetitionCategoryGridItem root, String tempDirectoryPathToSave)
}