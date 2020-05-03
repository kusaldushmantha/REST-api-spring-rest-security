package com.spring.rest.demo.rest;

/**
 * Purpose: Customer Not Found Custom Exception
 * Created By: Kusal Kankanamge
 * Created On: 03-May-2020
 */
public class CustomerNotFoundException extends RuntimeException
{
    public CustomerNotFoundException( String message )
    {
        super( message );
    }

    public CustomerNotFoundException()
    {
        super();
    }

    public CustomerNotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public CustomerNotFoundException( Throwable cause )
    {
        super( cause );
    }

    protected CustomerNotFoundException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
    {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
