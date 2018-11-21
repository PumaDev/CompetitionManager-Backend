package com.deathstar.competitionmanager.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = 'competition_category')
class CompetitionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    Integer id

    @Column(name = 'display_name', nullable = false)
    String displayName

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
}
