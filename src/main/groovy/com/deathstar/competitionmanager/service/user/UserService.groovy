package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User

interface UserService {

    User create(User user)

    User update(User user)

    User updatePassword(Integer userId, String newPassword)

    User findByLogin(String login)

    User findById(Integer id)

    User getNotNullUser(Integer id)

    List<User> findUsersByActivateStatus(ActivateStatus activateStatus)

    List<User> getAllUsers()
}