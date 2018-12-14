package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.security.extractor.AuthTokenExtractor
import com.deathstar.competitionmanager.service.accesstoken.AccessTokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CurrentUserResolverImpl implements CurrentUserResolver {

    @Autowired
    AuthTokenExtractor authTokenExtractor

    @Autowired
    AccessTokenService accessTokenService

    @Override
    User getCurrentUser() {
        String accessToken = authTokenExtractor.extractAuthToken()
        return accessTokenService.findByToken(accessToken)?.user
    }
}
