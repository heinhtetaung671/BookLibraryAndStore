package com.jdc.home.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jdc.home.model.CustomRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@ComponentScan("com.jdc.home.model")
@PropertySource("classpath:/database.properties")
@EnableJpaRepositories(basePackages = "com.jdc.home.model.repo", repositoryBaseClass = CustomRepositoryImpl.class)
public class DataBaseConfig {

	@Bean
	DataSource dataSource(@Value("${db.url}")String url, @Value("${db.user}")String user, @Value("${db.pass}") String pass) {
		var ds = new HikariDataSource();
		ds.setJdbcUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);
		return ds;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setPackagesToScan("com.jdc.home.model.entity");
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setJpaPropertyMap(Map.of("hibernate.hbm2ddl.auto", "update", "hibernate.format_sql", "true", "hibernate.show_sql", "true"));
		return bean;
	}
	
	@Bean
	TransactionManager  transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	
}
