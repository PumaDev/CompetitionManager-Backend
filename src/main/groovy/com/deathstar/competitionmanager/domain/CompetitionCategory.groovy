package com.deathstar.competitionmanager.domain

import groovy.transform.EqualsAndHashCode

import javax.persistence.*

@Entity
@Table(name = 'competition_category')
@EqualsAndHashCode
class CompetitionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    Integer id

    @Column(name = 'display_name', nullable = false)
    String displayName

    @Column(name = 'section', nullable = false)
    String section

    @Column(name = 'lower_age', nullable = true)
    Integer lowerAge

    @Column(name = 'upper_age', nullable = true)
    Integer upperAge

    @Column(name = 'lower_weight', nullable = true)
    Integer lowerWeight

    @Column(name = 'upper_weight', nullable = true)
    Integer upperWeight

    @Column(name = 'lower_experience', nullable = true)
    Integer lowerExperience

    @Column(name = 'upper_experience', nullable = true)
    Integer upperExperience

    @Column(name = 'male', nullable = true)
    Male male
}
