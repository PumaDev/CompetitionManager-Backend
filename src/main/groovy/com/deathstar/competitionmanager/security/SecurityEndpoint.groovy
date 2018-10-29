package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.user.UserRole

@interface SecurityEndpoint {

    UserRole[] rolesHasAccess()
    
}