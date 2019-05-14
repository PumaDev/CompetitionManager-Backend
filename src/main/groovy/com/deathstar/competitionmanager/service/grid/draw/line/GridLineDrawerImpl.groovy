package com.deathstar.competitionmanager.service.grid.draw.line

import com.deathstar.competitionmanager.service.grid.draw.GridDrawerConfig
import com.deathstar.competitionmanager.service.grid.draw.GridDrawerState
import com.deathstar.competitionmanager.service.grid.draw.GridSectionPosition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GridLineDrawerImpl implements GridLineDrawer {

    @Autowired
    GridDrawerConfig gridDrawerConfig

    @Override
    void drawLineBetweenSection(GridDrawerState gridDrawerState, GridSectionPosition parentSectionPosition, GridSectionPosition childSectionPosition) {
        int startXLinePosition = parentSectionPosition.x
        int startYLinePosition = parentSectionPosition.y + gridDrawerConfig.sectionHeight * 1.5

        int finishXLinePosition = childSectionPosition.x + gridDrawerConfig.sectionWidth
        int finishYLinePosition = childSectionPosition.y + gridDrawerConfig.sectionHeight * 1.5

        int mediumX = (startXLinePosition + finishXLinePosition) / 2

        gridDrawerState.ig2.drawLine(startXLinePosition, startYLinePosition, mediumX, startYLinePosition)
        gridDrawerState.ig2.drawLine(mediumX, startYLinePosition, mediumX, finishYLinePosition)
        gridDrawerState.ig2.drawLine(mediumX, finishYLinePosition, finishXLinePosition, finishYLinePosition)
    }
}
