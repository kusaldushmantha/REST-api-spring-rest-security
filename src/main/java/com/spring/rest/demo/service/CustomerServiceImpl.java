package com.spring.rest.demo.service;

import com.spring.rest.demo.dao.CustomerDAO;
import com.spring.rest.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created By: Kusal Kankanamge
 * Created On: 18-Apr-2020
 */
@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomer()
    {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer( Customer customer )
    {
        customerDAO.saveCustomer( customer );
    }

    @Override
    @Transactional
    public Customer getCustomer( int customerId )
    {
        Customer customer = customerDAO.getCustomer( customerId );
        return customer;
    }

    @Override
    @Transactional
    public void deleteCustomer( int customerId )
    {
        customerDAO.deleteCustomer( customerId );
    }
}
