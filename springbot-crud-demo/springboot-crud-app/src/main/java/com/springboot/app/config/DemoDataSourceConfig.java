package com.springboot.app.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

// This tells the app that we are using JPA repositories defined in the given package. 
// The package name is read from the application.properties file.
@Configuration
@EnableJpaRepositories(basePackages={"${spring.data.jpa.repository.packages}"})
public class DemoDataSourceConfig {
	
	
	// Spring Data JPA makes use of a entity manager factory bean and transacation manager. 
	// By default it will use a bean named, "entityManagerFactory". We manually configure this bean in this class. 
	// Also, by default, Spring Data JPA will use a bean named "transactionManager". The "transactionManager" bean is autoconfigured by Spring Boot.
	
	// create a datasource
	@Primary
	@Bean
	@ConfigurationProperties(prefix="app.datasource") // from app.properties
	public DataSource appDataSource() {
		return DataSourceBuilder.create().build();
	}

	// config entity manager factory
	// The entity manager factory tells Spring Data JPA which packages to scan for JPA entities.
	@Bean
	@ConfigurationProperties(prefix="spring.data.jpa.entity")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource appDataSource) {
		return builder
				.dataSource(appDataSource)
				.build();
	}

	// config datasource to access the security database
	// By default, Spring Security makes use of regular JDBC (no JPA)
	@Bean
	@ConfigurationProperties(prefix="security.datasource")
	public DataSource securityDataSource() {
		return DataSourceBuilder.create().build();
	}
}
