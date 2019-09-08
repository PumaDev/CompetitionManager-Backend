package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.view.user.*

interface UserViewService {

    RegistrateResponse register(CreateUserView createUserView)

    List<UserView> findUsersByActivateStatus(ActivateStatus activateStatus)

    List<UserView> getAllUsers()

    UserView getUserById(Integer userId)

    UserView setActivateStatusByUserId(ActivateStatus activateStatus, Integer userId)

    UserView updateUser(UpdateUserView userData)

    void updateUserPassword(Integer userId, UpdatePasswordView updatePasswordView)
}