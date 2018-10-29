package com.deathstar.competitionmanager.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'age_category')
class AgeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    Integer id

    @Column(name = 'low_age', nullable = true)
    Integer lowerAge

    @Column(name = 'upper_age', nullable = true)
    Integer upperAge

    @Column(name = 'display_name', nullable = false)
    String displayName
}
