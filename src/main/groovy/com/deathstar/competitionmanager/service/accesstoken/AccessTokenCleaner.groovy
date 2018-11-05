package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken
import groovy.util.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Log
@Component
class AccessTokenCleaner {

    @Autowired
    AccessTokenService accessTokenService

    @Scheduled(fixedDelay = 60000L)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    void cleanOldAccessTokens() {
        log.info('Start old tokens removing')
        Instant now = OffsetDateTime.now(ZoneOffset.UTC).toInstant()
        List<AccessToken> accessTokens = accessTokenService.getAll()
        List<AccessToken> accessTokensForRemoving = accessTokens.findAll { it.expiresTime < now }
        if (accessTokensForRemoving) {
            accessTokenService.deleteAll(accessTokensForRemoving)
        }
        log.info("Finish removing of ${accessTokensForRemoving.size()} access tokens")
    }
}
