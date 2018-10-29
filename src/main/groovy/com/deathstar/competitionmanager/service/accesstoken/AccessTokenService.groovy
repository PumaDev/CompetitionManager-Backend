package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken

interface AccessTokenService {

    AccessToken create(AccessToken accessToken)

    AccessToken findByToken(String token)

    boolean isAccessTokenValid(String token)

    void delete(String token)

}