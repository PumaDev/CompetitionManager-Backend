package com.deathstar.competitionmanager.domain.user

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'user')
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id', nullable = false)
    Integer id

    @Column(name = 'login', nullable = false)
    String login

    @Column(name = 'password', nullable = false)
    String password

    @Column(name = 'mail', nullable = false)
    String mail

    @Column(name = 'club_name', nullable = false)
    String clubName

    @Column(name = 'user_role', nullable = false)
    UserRole userRole

    @Column(name = 'activate_status', nullable = false)
    ActivateStatus activateStatus
}
