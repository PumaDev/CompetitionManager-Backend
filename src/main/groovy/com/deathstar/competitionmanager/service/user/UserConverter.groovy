package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.view.user.CreateUserView
import com.deathstar.competitionmanager.view.user.UpdateUserView
import com.deathstar.competitionmanager.view.user.UserView
import org.springframework.stereotype.Component

@Component
class UserConverter {

    UserView convertToView(User domainEntity) {
        UserView view = null
        if (domainEntity) {
            view = new UserView(
                    id: domainEntity.id,
                    login: domainEntity.login,
                    mail: domainEntity.mail,
                    activateStatus: domainEntity.activateStatus,
                    userRole: domainEntity.userRole
            )
        }
        return view
    }

    List<UserView> convertToViews(List<User> users) {
        return users.collect { convertToView(it) }
    }

    User convertCreateUserViewToUser(CreateUserView createUserView) {
        return new User(
                login: createUserView.login,
                mail: createUserView.mail,
                password: createUserView.password
        )
    }

    User convertUpdateUserViewToUser(UpdateUserView updateUserView) {
        return new User(
                id: updateUserView.id,
                login: updateUserView.login,
                mail: updateUserView.mail
        )
    }
}
