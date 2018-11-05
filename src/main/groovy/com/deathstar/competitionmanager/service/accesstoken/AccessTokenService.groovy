package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken

interface AccessTokenService {

    AccessToken save(AccessToken accessToken)

    AccessToken findByToken(String token)

    List<AccessToken> getAll()

    boolean isAccessTokenValid(String token)

    void delete(String token)

    void deleteAll(List<AccessToken> accessTokens)
}