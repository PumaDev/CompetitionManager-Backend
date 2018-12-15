package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.domain.user.UserRole
import com.deathstar.competitionmanager.security.SecurityEndpoint
import com.deathstar.competitionmanager.service.user.UserViewService
import com.deathstar.competitionmanager.view.user.UserView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    UserViewService userViewService

    @SecurityEndpoint(rolesHasAccess = [UserRole.DEVELOPER, UserRole.ADMIN])
    @GetMapping('/users/waiting-approve')
    List<UserView> getWaitingApproveUsers() {
        return userViewService.findUsersByActivateStatus(ActivateStatus.WAITING_APPROVE)
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.ADMIN])
    @PostMapping('/users/activate/{userId}')
    UserView activateUser(@PathVariable('userId') Integer userId) {
        return userViewService.setActivateStatusByUserId(ActivateStatus.ACTIVE, userId)
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.ADMIN])
    @PostMapping('/users/ban/{userId}')
    UserView banUser(@PathVariable('userId') Integer userId) {
        return userViewService.setActivateStatusByUserId(ActivateStatus.BANNED, userId)
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.ADMIN])
    @PostMapping('/users/block/{userId}')
    UserView blockUser(@PathVariable('userId') Integer userId) {
        return userViewService.setActivateStatusByUserId(ActivateStatus.BLOCKED, userId)
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.ADMIN, UserRole.DEVELOPER])
    @GetMapping('/users')
    List<UserView> getAllUsers() {
        return userViewService.getAllUsers()
    }

    @SecurityEndpoint(rolesHasAccess = [UserRole.ADMIN])
    @PutMapping('/users/{userId}/active-status/{activeStatus}')
    UserView updateActiveStatus(@PathVariable('userId') Integer userId,
                                @PathVariable('activeStatus') ActivateStatus newActiveStatus) {
        return userViewService.setActivateStatusByUserId(newActiveStatus, userId)
    }
}
