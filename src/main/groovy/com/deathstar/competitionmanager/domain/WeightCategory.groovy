package com.deathstar.competitionmanager.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'weight_category')
class WeightCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    Integer id

    @Column(name = 'low_weight', nullable = true)
    Integer lowerWeight

    @Column(name = 'upper_weight', nullable = true)
    Integer upperWeight

    @Column(name = 'display_name', nullable = false)
    String displayName
}
