package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.user.User

interface CurrentUserResolver {

    User getCurrentUser()
}