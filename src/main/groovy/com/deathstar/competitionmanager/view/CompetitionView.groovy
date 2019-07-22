package com.deathstar.competitionmanager.view

import com.deathstar.competitionmanager.domain.RegistrationStatus
import com.deathstar.competitionmanager.util.LocalDateJsonDeserializer
import com.deathstar.competitionmanager.util.LocalDateJsonSerializer
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import java.time.LocalDate

class CompetitionView {

    Integer id

    String name

    @JsonSerialize(using = LocalDateJsonSerializer)
    @JsonDeserialize(using = LocalDateJsonDeserializer)
    LocalDate startDate

    @JsonSerialize(using = LocalDateJsonSerializer)
    @JsonDeserialize(using = LocalDateJsonDeserializer)
    LocalDate endDate

    String description

    RegistrationStatus registrationStatus

    List<CompetitionCategoryView> categories

    CompetitionMetaView competitionMeta
}

