package com.spring.rest.demo.rest;

/**
 * Purpose: Custom customer errors response object
 * Created By: Kusal Kankanamge
 * Created On: 03-May-2020
 */
public class CustomerErrorResponse
{
    private int status;
    private String message;
    private long timestamp;

    public CustomerErrorResponse( int status, String message, long timestamp )
    {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus( int status )
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp( long timestamp )
    {
        this.timestamp = timestamp;
    }
}
