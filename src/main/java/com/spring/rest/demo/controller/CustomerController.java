package com.spring.rest.demo.controller;

import com.spring.rest.demo.entity.Customer;
import com.spring.rest.demo.rest.CustomerNotFoundException;
import com.spring.rest.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose: REST Controller
 * Created By: Kusal Kankanamge
 * Created On: 03-May-2020
 */
@RestController
@RequestMapping( "/api" )
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @GetMapping( "/customers" )
    public List<Customer> getCustomerList()
    {
        return customerService.getCustomer();
    }

    @GetMapping( "/customers/{customerId}" )
    public Customer getCustomer( @PathVariable int customerId )
    {
        Customer customer = customerService.getCustomer( customerId );
        if( customer == null )
        {
            throw new CustomerNotFoundException( "Customer not found for ID : " + customerId );
        }
        return customer;
    }

    @PostMapping( "/customers" )
    public Customer insertCustomer( @RequestBody Customer customer )
    {
        customer.setId( 0 ); // If the ID is 0, this will be an insert operation by saveOrUpdate
        customerService.saveCustomer( customer );
        return customer;
    }

    @PutMapping( "/customers/{customerId}" )
    public int deleteCustomer( @PathVariable int customerId )
    {
        Customer customer = customerService.getCustomer( customerId );
        if( customer == null )
        {
            throw new CustomerNotFoundException( "Customer not found for ID : " + customerId );
        }
        customerService.deleteCustomer( customerId );
        return customerId;
    }

    @DeleteMapping( "/customers" )
    public Customer updateCustomer( @RequestBody Customer customer )
    {
        customerService.saveCustomer( customer );
        return customer;
    }
}
