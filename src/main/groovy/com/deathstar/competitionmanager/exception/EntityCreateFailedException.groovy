package com.deathstar.competitionmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class EntityCreateFailedException extends RuntimeException {
    int errorCode

    EntityCreateFailedException(int errorCode, String message) {
        super(message)
        this.errorCode = errorCode
    }
}
