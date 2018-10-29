package com.deathstar.competitionmanager.domain

import com.deathstar.competitionmanager.util.JpaLocalDateConverter

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = 'competition')
class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    Integer id

    @Column(name = 'name', nullable = false)
    String name

    @Column(name = 'start_date', nullable = true)
    @Convert(converter = JpaLocalDateConverter)
    LocalDate startDate

    @Column(name = 'end_date', nullable = true)
    @Convert(converter = JpaLocalDateConverter)
    LocalDate endDate

    @Column(name = 'description', nullable = true)
    String description

    @Column(name = 'registration_status', nullable = false)
    RegistrationStatus registrationStatus

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name='competition_has_competition_category',
        joinColumns = [@JoinColumn(name='competition_id')],
        inverseJoinColumns = [@JoinColumn(name='category_id')])
    Set<CompetitionCategory> categories = new HashSet<>()
}
