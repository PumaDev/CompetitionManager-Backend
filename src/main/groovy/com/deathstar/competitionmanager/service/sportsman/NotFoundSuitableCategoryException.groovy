package com.deathstar.competitionmanager.service.sportsman

import com.deathstar.competitionmanager.exception.EntityCreateFailedException

class NotFoundSuitableCategoryException extends EntityCreateFailedException {

    NotFoundSuitableCategoryException() {
        super(4, 'Suitable category not found')
    }
}
