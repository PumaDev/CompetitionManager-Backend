package com.deathstar.competitionmanager.repository

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.view.user.UserView
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login)

    User findByClubName(String clubName)

    List<User> findByActivateStatus(ActivateStatus activateStatus)
}