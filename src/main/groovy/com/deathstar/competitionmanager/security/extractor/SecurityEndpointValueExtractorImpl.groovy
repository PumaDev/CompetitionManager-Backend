package com.deathstar.competitionmanager.security.extractor

import com.deathstar.competitionmanager.domain.user.UserRole
import com.deathstar.competitionmanager.security.SecurityEndpoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

import java.lang.reflect.Method

@Component
class SecurityEndpointValueExtractorImpl implements SecurityEndpointValueExtractor {

    @Override
    UserRole[] extractUserRoles(ProceedingJoinPoint pjp) {
        MethodSignature signature = pjp.signature as MethodSignature
        Method realMethod = pjp.target.class.getMethod(signature.method.name, signature.method.parameterTypes)
        SecurityEndpoint securityEndpoint = realMethod.getAnnotation(SecurityEndpoint)
        return securityEndpoint.rolesHasAccess()
    }
}
