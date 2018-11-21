package com.deathstar.competitionmanager.service.category

import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.util.ViewConverter
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.springframework.stereotype.Component

@Component
class CompetitionCategoryConverter extends ViewConverter<CompetitionCategory, CompetitionCategoryView> {
    @Override
    CompetitionCategoryView convertToView(CompetitionCategory domainEntity) {
        def view = null
        if (domainEntity) {
            view = new CompetitionCategoryView()
            view = copyFields(domainEntity, view) as CompetitionCategoryView
        }
        return view
    }

    @Override
    CompetitionCategory convertToDomainEntity(CompetitionCategoryView view) {
        def domainEntity = new CompetitionCategory()
        return copyFields(view, domainEntity) as CompetitionCategory
    }
}
