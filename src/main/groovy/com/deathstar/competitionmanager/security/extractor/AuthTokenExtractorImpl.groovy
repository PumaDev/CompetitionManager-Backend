package com.deathstar.competitionmanager.security.extractor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest

@Component
class AuthTokenExtractorImpl implements AuthTokenExtractor {

    @Value('${com.ds.competition-manager.x-auth-token.header-name}')
    String xAuthTokenHeaderName

    @Autowired
    HttpServletRequest httpServletRequest

    @Override
    String extractAuthToken() {
        return httpServletRequest.getHeader(xAuthTokenHeaderName)
    }
}
