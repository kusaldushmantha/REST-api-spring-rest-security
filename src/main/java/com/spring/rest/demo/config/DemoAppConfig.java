package com.spring.rest.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Purpose: Application Configurations
 * Created By: Kusal Kankanamge
 * Created On: 03-May-2020
 */
@Configuration
@ComponentScan( basePackages = "com.spring.rest.demo" )
@EnableWebMvc
@EnableTransactionManagement
@PropertySource( "classpath:persistence-mysql.properties" )
public class DemoAppConfig implements WebMvcConfigurer
{
    private final Logger LOGGER = Logger.getLogger( getClass().getName() );

    @Autowired
    private Environment env;

    @Bean
    public DataSource myDataSource()
    {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try
        {
            comboPooledDataSource.setDriverClass( env.getProperty( "jdbc.driver" ) );

            LOGGER.info( ">>>> JDBC URL : " + env.getProperty( "jdbc.url" ) );
            LOGGER.info( ">>>> JDBC USER : " + env.getProperty( "jdbc.user" ) );

            comboPooledDataSource.setJdbcUrl( env.getProperty( "jdbc.url" ) );
            comboPooledDataSource.setUser( env.getProperty( "jdbc.user" ) );
            comboPooledDataSource.setPassword( env.getProperty( "jdbc.password" ) );

            comboPooledDataSource.setInitialPoolSize( Integer.parseInt( env.getProperty( "connection.pool.initialPoolSize" ) ) );
            comboPooledDataSource.setMinPoolSize( Integer.parseInt( env.getProperty( "connection.pool.minPoolSize" ) ) );
            comboPooledDataSource.setMaxPoolSize( Integer.parseInt( env.getProperty( "connection.pool.maxPoolSize" ) ) );
            comboPooledDataSource.setMaxIdleTime( Integer.parseInt( env.getProperty( "connection.pool.maxIdleTime" ) ) );
        }
        catch( Exception e )
        {
            LOGGER.log( Level.SEVERE, e.getMessage(), e );
        }
        return comboPooledDataSource;
    }

    private Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.setProperty( "hibernate.dialect", env.getProperty( "hibernate.dialect" ) );
        properties.setProperty( "hibernate.show_sql", env.getProperty( "hibernate.show_sql" ) );
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource( myDataSource() );
        sessionFactoryBean.setPackagesToScan( env.getProperty( "hibernate.packagesToScan" ) );
        sessionFactoryBean.setHibernateProperties( getHibernateProperties() );
        return sessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager( SessionFactory sessionFactory )
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory( sessionFactory );
        return transactionManager;
    }
}
