package com.deathstar.competitionmanager.domain

import com.deathstar.competitionmanager.util.JpaInstantConverter

import javax.persistence.*
import java.sql.Blob
import java.time.Instant

@Entity
@Table(name = 'generated_grid')
class GeneratedGrid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id', nullable = false)
    Integer id
    @Column(name = 'grid_key', nullable = false)
    String key
    @Column(name = 'body', nullable = false)
    Blob body
    @Column(name = 'created_date', nullable = false)
    @Convert(converter = JpaInstantConverter)
    Instant createdDate
}
