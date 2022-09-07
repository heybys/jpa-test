package com.heybys.optimusamicus.shop.config;

import com.heybys.optimusamicus.common.config.CommonConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    basePackages = "com.heybys.optimusamicus.shop.repository",
    entityManagerFactoryRef = "shopEntityManagerFactory",
    transactionManagerRef = "shopTransactionManager")
public class ShopDataSourceConfig {

  private final CommonConfigFactory commonConfigFactory;

  @Value("${app.domain.shop.persistence-unit-name}")
  private String persistenceUnitName;

  @Value("${spring.datasource.hikari.shop.jdbc-url}")
  private String jdbcUrl;

  @Value("${spring.datasource.hikari.shop.username}")
  private String username;

  @Value("${spring.datasource.hikari.shop.password}")
  private String password;

  @Bean
  public HikariConfig shopHikariConfig() {

    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(jdbcUrl);
    hikariConfig.setUsername(username);
    hikariConfig.setPassword(password);

    return hikariConfig;
  }

  @Bean
  public DataSource shopDataSource() {
    return new HikariDataSource(shopHikariConfig());
  }

  @Bean
  public JdbcTemplate shopJdbcTemplate() {
    return new JdbcTemplate(shopDataSource());
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean shopEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean =
        commonConfigFactory.createEntityManagerFactoryBean();
    factoryBean.setDataSource(shopDataSource());
    factoryBean.setPersistenceUnitName(persistenceUnitName);
    factoryBean.setPackagesToScan("com.heybys.optimusamicus.shop.entity");
    return factoryBean;
  }

  @Bean
  public PlatformTransactionManager shopTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(shopEntityManagerFactory().getObject());
    return transactionManager;
  }
}
