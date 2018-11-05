package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.view.user.CreateUserView
import com.deathstar.competitionmanager.view.user.UserView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserViewServiceImpl implements UserViewService {

    @Autowired
    UserService userService

    @Autowired
    UserConverter userConverter

    @Autowired
    PasswordEncrypter passwordEncrypter

    @Override
    UserView register(CreateUserView createUserView) {
        User user = userConverter.convertCreateUserViewToUser(createUserView)
        user.password = passwordEncrypter.hashPassword(user)
        User createdUser = userService.create(user)
        return userConverter.convertToView(createdUser)
    }

    @Override
    List<UserView> findUsersByActivateStatus(ActivateStatus activateStatus) {
        List<User> users = userService.findUsersByActivateStatus(activateStatus)
        return userConverter.convertToViews(users)
    }

    @Override
    List<UserView> getAllUsers() {
        List<User> users = userService.getAllUsers()
        return userConverter.convertToViews(users)
    }

    @Override
    UserView activateUser(Integer userId) {
        return updateUserActivateStatus(userId, ActivateStatus.ACTIVE)
    }

    @Override
    UserView banUser(Integer userId) {
        return updateUserActivateStatus(userId, ActivateStatus.BANNED)
    }

    @Override
    UserView blockUser(Integer userId) {
        return updateUserActivateStatus(userId, ActivateStatus.BLOCKED)
    }

    private UserView updateUserActivateStatus(Integer userId, ActivateStatus newActivateStatus) {
        User user = userService.getNotNullUser(userId)
        user.setActivateStatus(newActivateStatus)
        User updatesUser = userService.update(user)
        return userConverter.convertToView(updatesUser)
    }
}
