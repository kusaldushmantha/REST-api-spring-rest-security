package com.spring.rest.demo.dao;

import com.spring.rest.demo.entity.Customer;

import java.util.List;

/**
 * Created By: Kusal Kankanamge
 * Created On: 18-Apr-2020
 */
public interface CustomerDAO
{
    List<Customer> getCustomers();

    void saveCustomer( Customer customer );

    Customer getCustomer( int customerId );

    void deleteCustomer( int customerId );
}
