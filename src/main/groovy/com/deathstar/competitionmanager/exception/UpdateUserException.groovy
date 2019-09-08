package com.deathstar.competitionmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UpdateUserException extends CompetitionManagerException {
    UpdateUserException(int errorCode) {
        super(errorCode)
    }

    UpdateUserException(int errorCode, String message) {
        super(errorCode, message)
    }

    UpdateUserException(ConflictEntityException conflictException) {
        super(conflictException.errorCode, conflictException.message)
    }
}
