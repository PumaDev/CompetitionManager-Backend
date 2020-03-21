package com.deathstar.competitionmanager.domain


import javax.persistence.*

@Entity
@Table(name = 'registrated_sportsman')
class RegistratedSportsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id', nullable = false)
    Integer id

    @Column(name = 'name', nullable = false)
    String name

    @Column(name = 'last_name', nullable = false)
    String lastName

    @Column(name = 'age', nullable = false)
    Integer age

    @Column(name = 'weight', nullable = false)
    Double weight

    @Column(name = 'experience', nullable = false)
    Integer experience

    @Column(name = 'male', nullable = false)
    Male male

    @Column(name = 'club_name', nullable = false)
    String clubName

    @Column(name = 'competition_id', nullable = false)
    Integer competitionId

    @Column(name = 'section', nullable = false)
    String section

    @Column(name = 'coach', nullable = false)
    String coach

    @ManyToOne
    @JoinColumn(name = 'competition_category_id', nullable = false)
    CompetitionCategory competitionCategory
}
