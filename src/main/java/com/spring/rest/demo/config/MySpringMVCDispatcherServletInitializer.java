package com.spring.rest.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Purpose: Dispatcher Servlet Initializer
 * Created By: Kusal Kankanamge
 * Created On: 03-May-2020
 */
public class MySpringMVCDispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer
{
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[] { DemoAppConfig.class };
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] { "/" };
    }
}
