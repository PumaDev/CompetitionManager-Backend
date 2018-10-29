package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.user.User

interface SecutiryService {

    User login(String login, String password)

    User findUserByAccessToken(String accessToken)
}