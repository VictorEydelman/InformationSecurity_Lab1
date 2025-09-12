package se.ifmo.InfoSec.service;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitializer implements ServletContextListener{
    @Bean
    public FilterRegistrationBean<SecurityFilter> customAuthFilter(){
        FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecurityFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
