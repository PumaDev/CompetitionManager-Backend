package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken
import com.deathstar.competitionmanager.repository.AccessTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import java.time.Instant

@Service
class AccessTokenServiceImpl implements AccessTokenService {

    @Value('${expires-seconds:3600}')
    int tokenLifeLength

    @Autowired
    AccessTokenRepository accessTokenRepository

    @Override
    AccessToken create(AccessToken accessToken) {
        accessToken.expiresTime = Instant.now().plusSeconds(tokenLifeLength)
        return accessTokenRepository.save(accessToken)
    }

    @Override
    AccessToken findByToken(String token) {
        return accessTokenRepository.findOne(token)
    }

    @Override
    boolean isAccessTokenValid(String token) {
        Instant now = Instant.now()
        AccessToken accessToken = accessTokenRepository.findOne(token)
        return accessToken.expiresTime > now
    }

    @Override
    void delete(String token) {
        accessTokenRepository.delete(token)
    }
}
