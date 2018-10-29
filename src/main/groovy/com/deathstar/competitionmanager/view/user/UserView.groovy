package com.deathstar.competitionmanager.view.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.UserRole

class UserView {
    Integer id
    String login
    String mail
    String clubName
    UserRole userRole
    ActivateStatus activateStatus
}
