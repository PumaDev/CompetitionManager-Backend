package com.deathstar.competitionmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class EntityCreateFailedException extends CompetitionManagerException {

    EntityCreateFailedException(int errorCode, String message) {
        super(errorCode, message)
    }
}
