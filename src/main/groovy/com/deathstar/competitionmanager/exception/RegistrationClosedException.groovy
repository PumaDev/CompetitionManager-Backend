package com.deathstar.competitionmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class RegistrationClosedException extends EntityCreateFailedException {

    RegistrationClosedException(Integer competitionId) {
        super(3, "Registartion for competition with id ${competitionId} was closed")
    }
}
