package com.project.warehouse.web;


import com.project.warehouse.event.model.AbstractDomainEvent;
import com.project.warehouse.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// exception as global

@RestControllerAdvice
public class RestExceptionRestControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RemoveAccountException.class, SaveAccountException.class})
    public AbstractDomainEvent handleInternalServerErrorRequest(Exception ex) {
        return ((AbstractAccountException) ex).getEvent();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {AccountNotFoundException.class})
    public AbstractDomainEvent handleNotFoundRequest(Exception ex) {
        return ((AbstractAccountException) ex).getEvent();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {AccountValidationException.class})
    public AbstractDomainEvent handleBadRequest(Exception ex) {
        return ((AbstractAccountException) ex).getEvent();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {IdentityValidationException.class})
    public AbstractDomainEvent handleForbiddenRequest(Exception ex) {
        return ((AbstractAccountException) ex).getEvent();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {ConflictSaveAccountException.class})
    public AbstractDomainEvent handleConflictRequest(Exception ex) {
        return ((AbstractAccountException) ex).getEvent();
    }

}