package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.exception.AccessDeniedException
import com.deathstar.competitionmanager.exception.ConflictEntityException
import com.deathstar.competitionmanager.exception.UpdateUserException
import com.deathstar.competitionmanager.security.CurrentUserResolver
import com.deathstar.competitionmanager.service.mail.MailService
import com.deathstar.competitionmanager.service.sportsman.RegistratedSportsmanService
import com.deathstar.competitionmanager.view.accesstoken.LoginView
import com.deathstar.competitionmanager.view.user.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserViewServiceImpl implements UserViewService {

    @Autowired
    UserService userService

    @Autowired
    UserConverter userConverter

    @Autowired
    PasswordEncrypter passwordEncrypter

    @Autowired
    MailService mailService

    @Autowired
    CurrentUserResolver currentUserResolver

    @Autowired
    RegistratedSportsmanService registratedSportsmanService

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
    UserView getUserById(Integer userId) {
        return userConverter.convertToView(verifyAccessForUpdateUser(userId))
    }

    @Override
    UserView setActivateStatusByUserId(ActivateStatus newActivateStatus, Integer userId) {
        User user = userService.getNotNullUser(userId)
        user.setActivateStatus(newActivateStatus)
        User updatedUser = userService.update(user)
        if (newActivateStatus == ActivateStatus.ACTIVE) {
            mailService.sentMailAboutAproveRegistration(updatedUser)
        }
        return userConverter.convertToView(updatedUser)
    }

    @Override
    @Transactional
    UserView updateUser(UpdateUserView updateUserView) {
        User existingUser = verifyAccessForUpdateUser(updateUserView.id)
        User newUserData = userConverter.convertUpdateUserViewToUser(updateUserView)
        try {
            newUserData.activateStatus = existingUser.activateStatus
            newUserData.userRole = existingUser.userRole
            User updatedUser = userService.update(newUserData)

            if (existingUser.clubName != updatedUser.clubName) {
                registratedSportsmanService.bulkUpdate(
                        registratedSportsmanService
                                .findSportsmanByClubName(existingUser.clubName)
                                .collect { it.clubName = updatedUser.clubName; it }
                )
            }

            return userConverter.convertToView(updatedUser)
        } catch (ConflictEntityException conflictException) {
            throw new UpdateUserException(conflictException)
        }
    }

    @Override
    void updateUserPassword(Integer userId, UpdatePasswordView updatePasswordView) {
        User user = verifyAccessForUpdateUser(userId)
        LoginView loginView = new LoginView(login: user.login, password: updatePasswordView.oldPassword)
        if (!passwordEncrypter.isPasswordEquals(loginView, user)) {
            throw new UpdateUserException(31, "Old password is not equals for existing password")
        }

        user.password = updatePasswordView.newPassword
        String newEncryptedPassword = passwordEncrypter.hashPassword(user)
        userService.updatePassword(userId, newEncryptedPassword)
    }

    private User verifyAccessForUpdateUser(Integer updateUserId) {
        User currentUser = currentUserResolver.getCurrentUser()
        if (updateUserId != currentUser.id) {
            throw new AccessDeniedException("Access Denied on update user")
        }
        return currentUser
    }
}
