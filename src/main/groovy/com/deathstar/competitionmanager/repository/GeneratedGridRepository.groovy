package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.GeneratedGrid
import org.springframework.data.jpa.repository.JpaRepository

import java.time.Instant

interface GeneratedGridRepository extends JpaRepository<GeneratedGrid, Integer> {

    GeneratedGrid findGeneratedGridByKey(String key)

    Set<GeneratedGrid> findGeneratedGridsByCreatedDateLessThan(Instant createdDate)

}