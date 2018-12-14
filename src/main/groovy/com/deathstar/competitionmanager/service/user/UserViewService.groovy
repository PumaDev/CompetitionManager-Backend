package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.view.user.CreateUserView
import com.deathstar.competitionmanager.view.user.RegistrateResponse
import com.deathstar.competitionmanager.view.user.UserView

interface UserViewService {

    RegistrateResponse register(CreateUserView createUserView)

    List<UserView> findUsersByActivateStatus(ActivateStatus activateStatus)

    List<UserView> getAllUsers()

    UserView setActivateStatusByUserId(ActivateStatus activateStatus, Integer userId)
}