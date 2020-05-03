package com.spring.rest.demo.dao;

import com.spring.rest.demo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO
{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> query =
                currentSession.createQuery( "from Customer order by lastName", Customer.class );
        List<Customer> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void saveCustomer( Customer customer )
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( customer );
    }

    @Override
    public Customer getCustomer( int customerId )
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = currentSession.get( Customer.class, customerId );
        return customer;
    }

    @Override
    public void deleteCustomer( int customerId )
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery( "delete from Customer where id=:theCustomerId" );
        query.setParameter( "theCustomerId", customerId );
        query.executeUpdate();
    }
}
