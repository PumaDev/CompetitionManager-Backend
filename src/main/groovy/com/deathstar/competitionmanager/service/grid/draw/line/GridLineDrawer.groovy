package com.deathstar.competitionmanager.service.grid.draw.line

import com.deathstar.competitionmanager.service.grid.draw.GridDrawerState
import com.deathstar.competitionmanager.service.grid.draw.GridSectionPosition

interface GridLineDrawer {

    void drawLineBetweenSection(GridDrawerState gridDrawerState, GridSectionPosition parentSectionPosition, GridSectionPosition childSectionPosition)
}
