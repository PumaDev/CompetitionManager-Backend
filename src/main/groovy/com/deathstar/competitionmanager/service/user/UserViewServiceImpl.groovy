package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.exception.ConflictEntityException
import com.deathstar.competitionmanager.view.user.CreateUserView
import com.deathstar.competitionmanager.view.user.RegistrateResponse
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
    RegistrateResponse register(CreateUserView createUserView) {
        User user = userConverter.convertCreateUserViewToUser(createUserView)
        user.password = passwordEncrypter.hashPassword(user)
        User createdUser
        try {
            createdUser = userService.create(user)
        } catch (ConflictEntityException conflictEntityException) {
            return new RegistrateResponse(
                    success: false,
                    errorCode: conflictEntityException.errorCode,
                    message: conflictEntityException.message)
        }
        UserView createdUserView = userConverter.convertToView(createdUser)
        return new RegistrateResponse(
                success: true,
                createsUser: createdUserView)
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
    UserView setActivateStatusByUserId(ActivateStatus newActivateStatus, Integer userId) {
        User user = userService.getNotNullUser(userId)
        user.setActivateStatus(newActivateStatus)
        User updatesUser = userService.update(user)
        return userConverter.convertToView(updatesUser)
    }
}
