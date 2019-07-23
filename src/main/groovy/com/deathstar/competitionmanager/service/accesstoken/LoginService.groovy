package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.view.accesstoken.AccessTokenView
import com.deathstar.competitionmanager.view.accesstoken.LoginView

interface LoginService {

    AccessTokenView login(LoginView loginView)

    AccessTokenView refreshToken()
}