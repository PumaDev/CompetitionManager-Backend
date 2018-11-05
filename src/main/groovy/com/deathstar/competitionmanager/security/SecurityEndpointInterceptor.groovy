package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.user.UserRole
import com.deathstar.competitionmanager.exception.AccessDeniedException
import com.deathstar.competitionmanager.security.extractor.AuthTokenExtractor
import com.deathstar.competitionmanager.security.extractor.SecurityEndpointValueExtractor
import groovy.util.logging.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Log
@Component
@Aspect
class SecurityEndpointInterceptor {

    @Autowired
    SecurityEndpointValueExtractor securityEndpointValueExtractor

    @Autowired
    AuthTokenExtractor authTokenExtractor

    @Autowired
    RolesChecker rolesChecker

    @Pointcut('execution(public * *(..))')
    void anyMethod() {}

    @Around('anyMethod() && @annotation(securityEndpoint)')
    Object invoke(final ProceedingJoinPoint pjp, SecurityEndpoint securityEndpoint) {
        List<UserRole> rolesWithAccessToEndpoint = securityEndpointValueExtractor.extractUserRoles(pjp) as List<UserRole>
        String xAuthToken = authTokenExtractor.extractAuthToken()

        if (rolesChecker.hasUserAccessToEndpoint(rolesWithAccessToEndpoint, xAuthToken)) {
            return pjp.proceed()
        } else {
            throw new AccessDeniedException('Access denied')
        }
    }
}
