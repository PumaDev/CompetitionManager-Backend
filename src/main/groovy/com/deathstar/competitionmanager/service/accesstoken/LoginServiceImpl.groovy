package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken
import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.exception.LoginException
import com.deathstar.competitionmanager.exception.UserNotActiveException
import com.deathstar.competitionmanager.security.CurrentUserResolver
import com.deathstar.competitionmanager.security.extractor.AuthTokenExtractor
import com.deathstar.competitionmanager.service.user.PasswordEncrypter
import com.deathstar.competitionmanager.service.user.UserService
import com.deathstar.competitionmanager.view.accesstoken.AccessTokenView
import com.deathstar.competitionmanager.view.accesstoken.LoginView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Autowired
    CurrentUserResolver currentUserResolver

    @Autowired
    AuthTokenExtractor authTokenExtractor

    @Override
    AccessTokenView login(LoginView loginView) {
        User foundUserInSystem = userService.findByLogin(loginView.login)
        if (!foundUserInSystem || !passwordEncrypter.isPasswordEquals(loginView, foundUserInSystem)) {
            throw new LoginException()
        }

        if (foundUserInSystem.activateStatus != ActivateStatus.ACTIVE) {
            throw new UserNotActiveException(foundUserInSystem.activateStatus)
        }
        AccessToken accessToken = accessTokenGenerator.generate(foundUserInSystem)
        accessToken = accessTokenService.save(accessToken)
        return accessTokenConverter.convertAccessTokenToView(accessToken)
    }

    @Transactional
    @Override
    AccessTokenView refreshToken() {
        User currentUser = currentUserResolver.getCurrentUser()
        String accessToken = authTokenExtractor.extractAuthToken()
        accessTokenService.delete(accessToken)
        AccessToken newAccessToken = accessTokenGenerator.generate(currentUser)
        newAccessToken = accessTokenService.save(newAccessToken)
        return accessTokenConverter.convertAccessTokenToView(newAccessToken)
    }
}