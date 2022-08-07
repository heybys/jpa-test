package com.heybys.optimusamicus.order.config;

import com.heybys.optimusamicus.common.config.CommonConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.heybys.optimusamicus.order.repository", entityManagerFactoryRef = "orderEntityManagerFactory", transactionManagerRef = "orderTransactionManager")
public class OrderDataSourceConfig {

  private final CommonConfigFactory commonConfigFactory;

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari.order")
  public HikariConfig orderHikariConfig() {
    return new HikariConfig();
  }

  @Bean
  public DataSource orderDataSource() {
    return new HikariDataSource(orderHikariConfig());
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean = commonConfigFactory.createEntityManagerFactoryBean();
    factoryBean.setDataSource(orderDataSource());
    factoryBean.setPersistenceUnitName("orderEntityManager");
    factoryBean.setPackagesToScan("com.heybys.optimusamicus.order.entity");
    return factoryBean;
  }

  @Bean
  public PlatformTransactionManager orderTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(orderEntityManagerFactory().getObject());
    return transactionManager;
  }
}
