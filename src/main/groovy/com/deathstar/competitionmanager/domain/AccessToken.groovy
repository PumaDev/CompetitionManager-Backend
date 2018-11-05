package com.deathstar.competitionmanager.domain

import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.util.JpaInstantConverter

import javax.persistence.*
import java.time.Instant

@Entity
@Table(name = 'access_token')
class AccessToken {

    @Id
    @Column(name = 'token', nullable = false)
    String token

    @ManyToOne
    @JoinColumn(name = 'user_id', nullable = false)
    User user

    @Column(name = 'expires_time', nullable = false)
    @Convert(converter = JpaInstantConverter)
    Instant expiresTime
}
