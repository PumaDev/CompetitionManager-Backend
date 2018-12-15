package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.exception.CompetitionManagerException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CompetitionManagerException)
    ResponseEntity<EntityCreateErrorDetails> handleEntitySaveFailedException(CompetitionManagerException exception, WebRequest webRequest) {
        def errorDetails = new EntityCreateErrorDetails(errorCode: exception.errorCode, message: exception.message)
        return new ResponseEntity<EntityCreateErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST)
    }
}

class EntityCreateErrorDetails {
    int errorCode
    String message
}
