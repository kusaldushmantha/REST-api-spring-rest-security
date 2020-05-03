package com.spring.rest.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;

/**
 * Purpose: This authorize the requests coming into Rest API
 * Created By: Kusal Kankanamge
 * Created On: 03-May-2020
 */
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
            .withUser( users.username( "john" ).password( "test123" ).roles( "EMPLOYEE" ) )
            .withUser( users.username( "mary" ).password( "test123" ).roles( "EMPLOYEE", "MANAGER", "ADMIN" ) )
            .withUser( users.username( "susan" ).password( "test123" ).roles( "EMPLOYEE", "ADMIN" ) );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        // Authenticate user and authorize the access to the entire API with all operations
        // irrespective of the roles
/*        http.authorizeRequests()
            .antMatchers("/api/customers/**").authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/

        // Authenticate user and authorize the access to the API based on role
        http.authorizeRequests()
            .antMatchers( HttpMethod.GET, "/api/customers" ).hasRole( "EMPLOYEE" )
            .antMatchers( HttpMethod.GET, "/api/customers/**" ).hasRole( "EMPLOYEE" )
            .antMatchers( HttpMethod.POST, "/api/customers" ).hasAnyRole( "MANAGER", "ADMIN" )
            .antMatchers( HttpMethod.POST, "/api/customers/**" ).hasAnyRole( "MANAGER", "ADMIN" )
            .antMatchers( HttpMethod.PUT, "/api/customers" ).hasAnyRole( "MANAGER", "ADMIN" )
            .antMatchers( HttpMethod.PUT, "/api/customers/**" ).hasAnyRole( "MANAGER", "ADMIN" )
            .antMatchers( HttpMethod.DELETE, "/api/customers/**" ).hasRole( "ADMIN" )
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
    }
}
