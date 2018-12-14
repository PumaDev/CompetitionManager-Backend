package com.deathstar.competitionmanager.domain

enum RegistrationStatus {
    OPEN(true),
    CLOSED(false),
    REOPEN(true)

    boolean canRegister

    RegistrationStatus(boolean canRegister) {
        this.canRegister = canRegister
    }
}