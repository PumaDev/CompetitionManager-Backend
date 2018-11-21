package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.service.category.CompetitionCategoryConverter
import com.deathstar.competitionmanager.util.ViewConverter
import com.deathstar.competitionmanager.view.CompetitionView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CompetitionConverter extends ViewConverter<Competition, CompetitionView> {

    @Autowired
    CompetitionCategoryConverter competitionCategoryConverter

    @Override
    CompetitionView convertToView(Competition domainEntity) {
        def view = null
        if (domainEntity) {
            view = new CompetitionView()
            view = copyFields(domainEntity, view, ['categories'] as Set<String>) as CompetitionView
            view.categories = competitionCategoryConverter.convertToViews(domainEntity.categories.asList())
        }
        return view
    }

    @Override
    Competition convertToDomainEntity(CompetitionView view) {
        def domainEntity = new Competition()
        domainEntity = copyFields(view, domainEntity, ['categories'] as Set<String>) as Competition
        domainEntity.categories = competitionCategoryConverter.convertToDomainEntities(view.categories)
        return domainEntity
    }
}
