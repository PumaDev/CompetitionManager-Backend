package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.AccessToken
import org.springframework.data.jpa.repository.JpaRepository

interface AccessTokenRepository extends JpaRepository<AccessToken, String> {

}
