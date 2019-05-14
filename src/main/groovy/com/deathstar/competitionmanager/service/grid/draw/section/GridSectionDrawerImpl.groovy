package com.deathstar.competitionmanager.service.grid.draw.section

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.service.grid.draw.DrawUtil
import com.deathstar.competitionmanager.service.grid.draw.GridDrawerConfig
import com.deathstar.competitionmanager.service.grid.draw.GridDrawerState
import com.deathstar.competitionmanager.service.grid.draw.GridSectionPosition
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridSportsman
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.awt.Font
import java.awt.FontMetrics

@Service
class GridSectionDrawerImpl implements GridSectionDrawer {

    @Autowired
    GridDrawerConfig gridDrawerConfig

    @Override
    void draw(GridDrawerState gridDrawerState, CompetitionCategoryGridItem competitionCategoryGridItem, GridSectionPosition gridSectionPosition) {
        gridDrawerState.ig2.drawRect(gridSectionPosition.x, gridSectionPosition.y + gridDrawerConfig.sectionHeight, gridDrawerConfig.sectionWidth, gridDrawerConfig.sectionHeight)

        if (competitionCategoryGridItem instanceof CompetitionCategoryGridSportsman) {
            RegistratedSportsman sportsman = ((CompetitionCategoryGridSportsman) competitionCategoryGridItem).sportsman
            String sportsmanName = String.format("%s %s", sportsman.name, sportsman.lastName)
            String clubText = String.format("(%s)", sportsman.clubName)

            Font font = new Font("TimesRoman", Font.BOLD, gridDrawerConfig.sectionPenSize)
            gridDrawerState.ig2.setFont(font)
            FontMetrics fontMetrics = gridDrawerState.ig2.getFontMetrics()
            int textHeight = fontMetrics.getAscent()

            int halphSectionHeight = gridDrawerConfig.sectionHeight / 2

            int startXofSportsmanName = DrawUtil.getXPositionOfText(gridSectionPosition.x, gridDrawerConfig.sectionWidth, fontMetrics.stringWidth(sportsmanName))
            int startYofSportsmanName = DrawUtil.getYPositionOfText(gridSectionPosition.y, halphSectionHeight, textHeight) + gridDrawerConfig.sectionHeight
            gridDrawerState.ig2.drawString(sportsmanName, startXofSportsmanName, startYofSportsmanName)

            int startXOfClub = DrawUtil.getXPositionOfText(gridSectionPosition.x, gridDrawerConfig.sectionWidth, fontMetrics.stringWidth(clubText))
            int startYOfClub = DrawUtil.getYPositionOfText(gridSectionPosition.y + halphSectionHeight, halphSectionHeight, textHeight) + gridDrawerConfig.sectionHeight
            gridDrawerState.ig2.drawString(clubText, startXOfClub, startYOfClub)
        }
    }
}