package com.deluxeperfumes.order.exceptions;

import com.deluxeperfumes.perfume.exceptions.PerfumeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {
            PerfumeNotFoundException.class
    })
    private String handleNotFoundException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return exception.getMessage();
    }
}
