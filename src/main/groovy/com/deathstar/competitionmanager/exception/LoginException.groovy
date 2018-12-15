package com.deathstar.competitionmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class LoginException extends CompetitionManagerException {
    LoginException() {
        super(10, 'Login failed')
    }
}
