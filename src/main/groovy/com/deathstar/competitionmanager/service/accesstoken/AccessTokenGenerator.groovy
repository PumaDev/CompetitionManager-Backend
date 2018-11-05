package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken
import com.deathstar.competitionmanager.domain.user.User

interface AccessTokenGenerator {

    AccessToken generate(User user)
}