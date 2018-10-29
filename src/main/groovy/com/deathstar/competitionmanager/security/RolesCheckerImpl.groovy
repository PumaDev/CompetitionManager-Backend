package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.AccessToken
import com.deathstar.competitionmanager.domain.user.UserRole
import com.deathstar.competitionmanager.service.accesstoken.AccessTokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RolesCheckerImpl implements RolesChecker {

    @Autowired
    AccessTokenService accessTokenService

    @Override
    boolean hasUserAccessToEndpoint(List<UserRole> roles, String xAuthToken) {
        AccessToken accessToken = accessTokenService.findByToken(xAuthToken)
        return accessToken && roles.contains(accessToken.user.userRole)
    }
}
