package com.deathstar.competitionmanager.util

import com.deathstar.competitionmanager.exception.ConflictEntityException

class ExceptionUtil {

    static void throwConflictEntityExceptionIfNull(object, String message = "Created entity has conflict") {
        if (object) {
            throw new ConflictEntityException(message)
        }
    }
}
