package com.rest.template.springbootresttemplateex.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiErrorHandler {

    private HttpStatus status;
    private String message;

}
