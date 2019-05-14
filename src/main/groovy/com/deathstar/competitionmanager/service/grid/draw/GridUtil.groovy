package com.deathstar.competitionmanager.service.grid.draw

import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItemPair
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridSportsman
import org.springframework.stereotype.Component

@Component
class GridUtil {

    int getDeep(CompetitionCategoryGridItem item, deep = 0) {
        if (item.class == CompetitionCategoryGridSportsman) {
            return deep + 1
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