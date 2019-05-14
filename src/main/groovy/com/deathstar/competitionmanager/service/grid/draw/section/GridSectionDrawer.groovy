package com.deathstar.competitionmanager.service.grid.draw.section

import com.deathstar.competitionmanager.service.grid.draw.GridDrawerState
import com.deathstar.competitionmanager.service.grid.draw.GridSectionPosition
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem

interface GridSectionDrawer {
    void draw(GridDrawerState gridDrawerState, CompetitionCategoryGridItem competitionCategoryGridItem, GridSectionPosition gridSectionPosition)
}