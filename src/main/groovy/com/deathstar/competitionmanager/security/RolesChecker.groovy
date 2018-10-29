package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.user.UserRole

interface RolesChecker {

    boolean hasUserAccessToEndpoint(List<UserRole> roles, String xAuthToken)
}
