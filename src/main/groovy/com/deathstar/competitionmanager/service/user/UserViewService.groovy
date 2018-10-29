package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.view.user.CreateUserView
import com.deathstar.competitionmanager.view.user.UserView

interface UserViewService {

    UserView register(CreateUserView createUserView)

    UserView login(String login, String password)

    List<UserView> findUsersByActivateStatus(ActivateStatus activateStatus)

    List<UserView> getAllUsers()

    UserView activateUser(Integer userId)

    UserView banUser(Integer userId)

    UserView blockUser(Integer userId)
}