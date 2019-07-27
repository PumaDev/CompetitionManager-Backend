package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.GeneratedGrid

import java.time.Instant

interface GeneratedGridService {

    GeneratedGrid save(GeneratedGrid generatedGrid)

    GeneratedGrid findByKey(String key)

    Set<GeneratedGrid> findByLessThenCreatedDate(Instant createdDate)

    boolean deleteGeneratedGridsByIds(Set<GeneratedGrid> gridsForDelete)
}
