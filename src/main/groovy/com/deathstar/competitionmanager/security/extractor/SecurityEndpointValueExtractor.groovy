package com.deathstar.competitionmanager.security.extractor

import com.deathstar.competitionmanager.domain.user.UserRole
import org.aspectj.lang.ProceedingJoinPoint

interface SecurityEndpointValueExtractor {

    UserRole[] extractUserRoles(ProceedingJoinPoint proceedingJoinPoint)
}