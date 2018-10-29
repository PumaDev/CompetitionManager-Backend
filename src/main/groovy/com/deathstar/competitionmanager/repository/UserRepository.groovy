package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login)

    List<User> findByActivateStatus(ActivateStatus activateStatus)
}