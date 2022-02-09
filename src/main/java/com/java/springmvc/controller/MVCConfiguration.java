package com.java.springmvc.controller;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.java.springmvc.dao.ContactDaoImpl;
import com.java.springmvc.dao.InterfaceContact;

@Configuration
@ComponentScan(basePackages = "com.java.springmvc.controller")
@EnableWebMvc

public class MVCConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	
	public void addResourceHandler(ResourceHandlerRegistry handlerRegistry) {
		handlerRegistry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/Javed");
		dataSource.setUsername("root");
		dataSource.setPassword("Javed@010");
		return dataSource;
	}
	
	@Bean
	public InterfaceContact getContactDao() {
		return new ContactDaoImpl(getDataSource());
	}
}
