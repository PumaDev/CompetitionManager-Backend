package com.deathstar.competitionmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ConflictEntityException extends RuntimeException {

    int errorCode

    ConflictEntityException(String message) {
        super(message)
    }

    ConflictEntityException(int errorCode, String message) {
        super(message)
        this.errorCode = errorCode
    }
}
