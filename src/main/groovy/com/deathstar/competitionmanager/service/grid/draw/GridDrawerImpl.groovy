package com.deathstar.competitionmanager.service.grid.draw

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.service.grid.draw.line.GridLineDrawer
import com.deathstar.competitionmanager.service.grid.draw.section.GridSectionDrawer
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItemPair
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import javax.imageio.ImageIO
import java.awt.*
import java.awt.image.BufferedImage

@Component
class GridDrawerImpl implements GridDrawer {

    @Autowired
    GridDrawerConfig gridDrawerConfig

    @Autowired
    GridUtil gridUtil

    @Autowired
    GridSectionDrawer gridSectionDrawer

    @Autowired
    GridLineDrawer gridLineDrawer

    // TODO: maybe made it parameter configurable for user?
    @Value('${com.ds.competition-manager.grid-generation.file-extension:png}')
    String gridsFileExtension

    @Override
    File drawGridToFile(CompetitionCategory competitionCategory, CompetitionCategoryGridItem root, String tempDirectoryPath) {
        BufferedImage bi = new BufferedImage(gridDrawerConfig.listWidth, gridDrawerConfig.listHeight, BufferedImage.TYPE_INT_ARGB)
        Graphics2D ig2 = bi.createGraphics()

        drawTitle(ig2, competitionCategory)
        drawGrid(ig2, root)

        return saveImageToFile(bi, competitionCategory, tempDirectoryPath)
    }

    void drawTitle(Graphics2D ig2, CompetitionCategory competitionCategory) {
        ig2.setPaint(Color.black)

        Font font = new Font("TimesRoman", Font.BOLD, gridDrawerConfig.titlePenSize)
        ig2.setFont(font)

        String title = String.format("Категория: %s", competitionCategory.displayName)

        ig2.setPaint(gridDrawerConfig.titlePenColor)
        ig2.drawString(title, gridDrawerConfig.titleWidthTab, gridDrawerConfig.titleHeightTab)
    }

    void drawGrid(Graphics2D ig2, CompetitionCategoryGridItem root) {
        FontMetrics fontMetrics = ig2.getFontMetrics()
        int titleHeight = fontMetrics.getAscent()

        int deep = gridUtil.getDeep(root)
        int sportsmenCount = gridUtil.getSportsmanCount(root)

        int gridWidth = deep * gridDrawerConfig.sectionWidth + (deep - 1) * gridDrawerConfig.lineWidth * 2
        int gridHeight = sportsmenCount * gridDrawerConfig.sectionHeight + (sportsmenCount - 1) * gridDrawerConfig.heightSpace

        int gridHeightUpperOffset = gridDrawerConfig.titleTab * 2 + titleHeight
        int gridWidthLeftOffset = gridDrawerConfig.space

        int gridHeightDownOffset = gridHeightUpperOffset + gridHeight
        int gridWidthRightOffset = gridWidthLeftOffset + gridWidth

        int gridHeightStartPosition = (gridHeightDownOffset - gridDrawerConfig.sectionHeight) / 2
        int gridWidthStartPosition = (gridWidthRightOffset - gridDrawerConfig.sectionWidth)

        GridDrawerState gridDrawerState = new GridDrawerState(ig2: ig2)
        GridSectionPosition gridSectionPosition = new GridSectionPosition(x: gridWidthStartPosition, y: gridHeightStartPosition)
        gridSectionDrawer.draw(gridDrawerState, root, gridSectionPosition)
        drawChilds(gridDrawerState, root, gridSectionPosition)
    }

    void drawChilds(GridDrawerState gridDrawerState, CompetitionCategoryGridItem item, GridSectionPosition gridSectionPosition, int deep = 1) {
        if (item instanceof CompetitionCategoryGridItemPair) {
            CompetitionCategoryGridItemPair pair = (CompetitionCategoryGridItemPair) item
            if (pair.left) {
                GridSectionPosition leftChildPosition = calculateLeftChildSection(gridSectionPosition, deep)
                gridSectionDrawer.draw(gridDrawerState, pair.left, leftChildPosition)
                gridLineDrawer.drawLineBetweenSection(gridDrawerState, gridSectionPosition, leftChildPosition)
                drawChilds(gridDrawerState, pair.left, leftChildPosition, deep + 1)
            }
            if (pair.right) {
                GridSectionPosition rightChildPosition = calculateRightChildSection(gridSectionPosition, deep)
                gridSectionDrawer.draw(gridDrawerState, pair.right, rightChildPosition)
                gridLineDrawer.drawLineBetweenSection(gridDrawerState, gridSectionPosition, rightChildPosition)
                drawChilds(gridDrawerState, pair.right, rightChildPosition, deep + 1)
            }
        }
    }


    GridSectionPosition calculateLeftChildSection(GridSectionPosition parentPosition, int deep) {
        int childX = parentPosition.x - (gridDrawerConfig.sectionWidth + gridDrawerConfig.space)
        int childY = parentPosition.y - (gridDrawerConfig.sectionHeight / deep + gridDrawerConfig.heightSpace / (2 * deep))

        return new GridSectionPosition(x: childX, y: childY)
    }

    GridSectionPosition calculateRightChildSection(GridSectionPosition parentPosition, int deep) {
        int childX = parentPosition.x - (gridDrawerConfig.sectionWidth + gridDrawerConfig.space)
        int childY = parentPosition.y + (gridDrawerConfig.sectionHeight / deep + gridDrawerConfig.heightSpace / (2 * deep))

        return new GridSectionPosition(x: childX, y: childY)
    }

    File saveImageToFile(BufferedImage bi, CompetitionCategory competitionCategory, String tempDirectoryPath) {
        File gridFile = new File(String.format("%s/%s.%s", tempDirectoryPath, competitionCategory.displayName, gridsFileExtension))
        ImageIO.write(bi, gridsFileExtension.toUpperCase(), gridFile)

        return gridFile
    }
}
