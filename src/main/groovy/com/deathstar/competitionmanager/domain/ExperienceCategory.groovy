package com.deathstar.competitionmanager.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'experience_category')
class ExperienceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    Integer id

    @Column(name = 'display_name', nullable = false)
    String displayName
}
