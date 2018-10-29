package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.User

interface PasswordEncrypter {

    String hashPassword(User user)

}