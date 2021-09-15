package com.kimmy.ExpenseTrackerREStAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ApiRequestExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class, IOException.class})
    public ResponseEntity<Object> responseEntity (ApiRequestException ex){
        ApiException apiException = new ApiException(
                ex.getMessage(), HttpStatus.BAD_REQUEST
        );
        return  new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}


