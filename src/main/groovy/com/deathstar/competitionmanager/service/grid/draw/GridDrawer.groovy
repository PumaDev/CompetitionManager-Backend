package com.deathstar.competitionmanager.service.grid.draw

import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem

interface GridDrawer {
    File drawGridToFile(CompetitionCategoryGridItem root)
}