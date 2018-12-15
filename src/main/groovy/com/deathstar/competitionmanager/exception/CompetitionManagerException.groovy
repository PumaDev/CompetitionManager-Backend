package com.deathstar.competitionmanager.exception

class CompetitionManagerException extends RuntimeException {

    int errorCode

    CompetitionManagerException(int errorCode) {
        this.errorCode = errorCode
    }

    CompetitionManagerException(int errorCode, String message) {
        super(message)
        this.errorCode = errorCode
    }
}
