package com.deathstar.competitionmanager.service.grid.draw

import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItemPair
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridSportsman
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.awt.*
import java.awt.image.BufferedImage

@Component
class GridDrawerImpl implements GridDrawer {

    @Autowired
    GridDrawerConfig gridDrawerConfig

    @Autowired
    GridUtil gridUtil

    @Override
    File drawGridToFile(CompetitionCategoryGridItem root) {
        BufferedImage bi = new BufferedImage(gridDrawerConfig.listWidth, gridDrawerConfig.listHeight, BufferedImage.TYPE_INT_ARGB)

        Graphics2D ig2 = bi.createGraphics()

        ig2.setPaint(Color.black)

        int deep = gridUtil.getDeep(root)
        int sportsmenCount = gridUtil.getSportsmanCount(root)

        int gridWidth = (1 + deep) * gridDrawerConfig.sectionWidth + deep * gridDrawerConfig.lineWidth * 2
        int gridHeight = sportsmenCount * gridDrawerConfig.sectionHeight + (sportsmenCount - 1) * gridDrawerConfig.space


        return null
    }
}

@Component
class GridUtil {

    int getDeep(CompetitionCategoryGridItem item, deep = 0) {
        if (item.class == CompetitionCategoryGridSportsman) {
            return deep
        } else {
            def pair = (CompetitionCategoryGridItemPair) item
            int leftDeep = getDeep(pair.left, deep + 1)
            int rightDeep = getDeep(pair.right, deep + 1)
            return Math.max(leftDeep, rightDeep)
        }
    }

    int getSportsmanCount(CompetitionCategoryGridItem item) {
        if (item.class == CompetitionCategoryGridSportsman) {
            return 1
        } else {
            def pair = (CompetitionCategoryGridItemPair) item
            return getSportsmanCount(pair.left) + getSportsmanCount(pair.right)
        }
    }
}
