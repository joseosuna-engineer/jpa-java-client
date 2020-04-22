package com.prottonne.jpa.exception;

import com.prottonne.jpa.dto.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    public GeneralExceptionHandler() {
        super();
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response dataIntegrityViolationException(Exception ex) {

        // TODO
        return new Response();
    }

    @ExceptionHandler(value = IncorrectResultSizeDataAccessException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response incorrectResultSizeDataAccessException(Exception ex) {

        // TODO
        return new Response();
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response emptyResultDataAccessException(Exception ex) {

        // TODO
        return new Response();
    }

}
