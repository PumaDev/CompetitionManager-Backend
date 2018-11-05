package com.deathstar.competitionmanager.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class AccessDeniedException extends RuntimeException {
    AccessDeniedException(String message) {
        super(message)
    }
}
