package com.spring.rest.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Purpose: Exception handler for controllers
 * Created By: Kusal Kankanamge
 * Created On: 03-May-2020
 */
@ControllerAdvice
public class CustomerRestExceptionHandler
{
    /**
     * Handle customer not found exception scenarios
     *
     * @param e {@link CustomerNotFoundException}
     * @return {@link ResponseEntity<CustomerErrorResponse>}
     */
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException( CustomerNotFoundException e )
    {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse( HttpStatus.NOT_FOUND.value(),
                e.getMessage(), System.currentTimeMillis() );
        return new ResponseEntity<>( customerErrorResponse, HttpStatus.NOT_FOUND );
    }

    /**
     * Handle any exception scenarios
     *
     * @param e {@link Exception}
     * @return {@link ResponseEntity<CustomerErrorResponse>}
     */
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException( Exception e )
    {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse( HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), System.currentTimeMillis() );
        return new ResponseEntity<>( customerErrorResponse, HttpStatus.BAD_REQUEST );
    }
}
