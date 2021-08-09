package com.epam.training.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

@Configuration
@EnableJpaRepositories(basePackages = {"com.epam.training.dao"})
public class HibernateConfig {

   private static final ResourceBundle resource = ResourceBundle.getBundle("application");

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager
                = new LocalContainerEntityManagerFactoryBean();
        entityManager.destroy();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("com.epam.training.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setJpaProperties(additionalProperties());

        return entityManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", resource.getString("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", resource.getString("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", resource.getString("hibernate.show_sql"));
        return properties;
    }


    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(resource.getString("jdbc.driver.name"));
        hikariConfig.setJdbcUrl(resource.getString("jdbc.url"));
        hikariConfig.setUsername(resource.getString("jdbc.user"));
        hikariConfig.setPassword(resource.getString("jdbc.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(
                Objects.requireNonNull(resource.getString("jdbc.hikari.maximum-pool-size"))));
        hikariConfig.addDataSourceProperty("spring.jpa.generate-ddl", true);
        return new HikariDataSource(hikariConfig);
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
