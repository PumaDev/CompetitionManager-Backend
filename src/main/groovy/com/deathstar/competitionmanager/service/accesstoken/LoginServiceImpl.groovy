package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken
import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.service.user.PasswordEncrypter
import com.deathstar.competitionmanager.service.user.UserService
import com.deathstar.competitionmanager.view.accesstoken.AccessTokenView
import com.deathstar.competitionmanager.view.accesstoken.LoginView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl implements LoginService {

    @Autowired
    UserService userService

    @Autowired
    AccessTokenService accessTokenService

    @Autowired
    PasswordEncrypter passwordEncrypter

    @Autowired
    AccessTokenGenerator accessTokenGenerator

    @Autowired
    AccessTokenConverter accessTokenConverter

    @Override
    AccessTokenView login(LoginView loginView) {
        User foundUserInSystem = userService.findByLogin(loginView.login)
        if (!passwordEncrypter.isPasswordEquals(loginView, foundUserInSystem)) {
            throw new Exception('Login failed')
        }
        AccessToken accessToken = accessTokenGenerator.generate(foundUserInSystem)
        accessToken = accessTokenService.save(accessToken)
        return accessTokenConverter.convertAccessTokenToView(accessToken)
    }
}