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
    String id

    @ManyToOne
    @JoinColumn(name = 'weight_category', nullable = false)
    WeightCategory weightCategory

    @ManyToOne
    @JoinColumn(name = 'experience_category', nullable = false)
    ExperienceCategory experienceCategory

    @ManyToOne
    @JoinColumn(name = 'age_category', nullable = false)
    AgeCategory ageCategory
}
