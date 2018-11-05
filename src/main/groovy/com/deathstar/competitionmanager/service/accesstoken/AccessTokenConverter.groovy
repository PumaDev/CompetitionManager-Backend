package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken
import com.deathstar.competitionmanager.service.user.UserConverter
import com.deathstar.competitionmanager.view.accesstoken.AccessTokenView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AccessTokenConverter {

    @Autowired
    UserConverter userConverter

    AccessTokenView convertAccessTokenToView(AccessToken accessToken) {
        AccessTokenView accessTokenView = null
        if (accessToken) {
            accessTokenView = new AccessTokenView(
                    user: userConverter.convertToView(accessToken.user),
                    token: accessToken.token
            )
        }
        return accessTokenView
    }

}
