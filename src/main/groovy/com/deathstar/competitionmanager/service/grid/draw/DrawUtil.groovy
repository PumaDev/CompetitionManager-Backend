package com.deathstar.competitionmanager.service.grid.draw

class DrawUtil {
    static int getXPositionOfText(int start, int sectionSize, int textSize) {
        return start + (sectionSize - textSize) / 2
    }

    static int getYPositionOfText(int start, int sectionHeight, int textHeight) {
        return start + sectionHeight / 2 + textHeight / 4
    }
}