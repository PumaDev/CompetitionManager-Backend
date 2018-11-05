package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.view.accesstoken.LoginView

interface PasswordEncrypter {

    String hashPassword(User user)

    Boolean isPasswordEquals(LoginView loginView, User user)
}