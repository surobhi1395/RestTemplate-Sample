package com.rest.template.springbootresttemplateex.advice;

import com.rest.template.springbootresttemplateex.exception.ApiErrorHandler;
import com.rest.template.springbootresttemplateex.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ApiErrorHandler> handleBadRequestException(Exception ex){
        log.info("Exception caught: ", ex);
        return new ResponseEntity<>(new ApiErrorHandler(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
