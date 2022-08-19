package com.heybys.optimusamicus.user.config;

import com.heybys.optimusamicus.common.config.CommonConfigFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.heybys.optimusamicus.user.repository",
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userTransactionManager")
public class UserDataSourceConfig {

  private final CommonConfigFactory commonConfigFactory;

  @Value("${app.domain.user.persistence-unit-name}")
  private String persistenceUnitName;

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari.user")
  public HikariConfig userHikariConfig() {
    return new HikariConfig();
  }

  @Primary
  @Bean
  public DataSource userDataSource() {
    return new HikariDataSource(userHikariConfig());
  }

  @Primary
  @Bean
  public JdbcTemplate userJdbcTemplate() {
    return new JdbcTemplate(userDataSource());
  }

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean =
        commonConfigFactory.createEntityManagerFactoryBean();
    factoryBean.setDataSource(userDataSource());
    factoryBean.setPersistenceUnitName(persistenceUnitName);
    factoryBean.setPackagesToScan("com.heybys.optimusamicus.user.entity");
    return factoryBean;
  }

  @Primary
  @Bean
  public PlatformTransactionManager userTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(userEntityManagerFactory().getObject());
    return transactionManager;
  }

  @Primary
  @Bean
  public JPAQueryFactory userJPAQueryFactory() {
    return new JPAQueryFactory(
        Objects.requireNonNull(userEntityManagerFactory().getObject()).createEntityManager());
  }
}
