package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.util.ViewConverter
import com.deathstar.competitionmanager.view.CompetitionView
import org.springframework.stereotype.Component

@Component
class CompetitionConverter extends ViewConverter<Competition, CompetitionView> {

    @Override
    CompetitionView convertToView(Competition domainEntity) {
        def view = null
        if (domainEntity) {
            view = new CompetitionView()
            view = copyFields(domainEntity, view, ['categories'] as Set<String>) as CompetitionView
        }
        return view
    }

    @Override
    Competition convertToDomainEntity(CompetitionView view) {
        def domainEntity = new Competition()
        return copyFields(view, domainEntity) as Competition
    }
}
