package com.heybys.optimusamicus.billing.config;

// import com.heybys.optimusamicus.common.config.CommonConfigFactory;
// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;
// import javax.sql.DataSource;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Configuration
// @RequiredArgsConstructor
// @EnableTransactionManagement
// @EnableJpaRepositories(
//     basePackages = "com.heybys.optimusamicus.billing.repository",
//     entityManagerFactoryRef = "billingEntityManagerFactory",
//     transactionManagerRef = "billingTransactionManager")
public class BillingDataSourceConfig {

  // private final CommonConfigFactory commonConfigFactory;
  //
  // @Value("${app.domain.billing.persistence-unit-name}")
  // private String persistenceUnitName;
  //
  // @Value("${spring.datasource.hikari.billing.jdbc-url}")
  // private String jdbcUrl;
  //
  // @Value("${spring.datasource.hikari.billing.username}")
  // private String username;
  //
  // @Value("${spring.datasource.hikari.billing.password}")
  // private String password;
  //
  // @Bean
  // public HikariConfig billingHikariConfig() {
  //
  //   HikariConfig hikariConfig = new HikariConfig();
  //   hikariConfig.setJdbcUrl(jdbcUrl);
  //   hikariConfig.setUsername(username);
  //   hikariConfig.setPassword(password);
  //
  //   return hikariConfig;
  // }
  //
  // @Bean
  // public DataSource billingDataSource() {
  //   return new HikariDataSource(billingHikariConfig());
  // }
  //
  // @Bean
  // public JdbcTemplate billingJdbcTemplate() {
  //   return new JdbcTemplate(billingDataSource());
  // }
  //
  // @Bean
  // public LocalContainerEntityManagerFactoryBean billingEntityManagerFactory() {
  //   LocalContainerEntityManagerFactoryBean factoryBean =
  //       commonConfigFactory.createEntityManagerFactoryBean();
  //   factoryBean.setDataSource(billingDataSource());
  //   factoryBean.setPersistenceUnitName(persistenceUnitName);
  //   factoryBean.setPackagesToScan("com.heybys.optimusamicus.billing.entity");
  //   return factoryBean;
  // }
  //
  // @Bean
  // public PlatformTransactionManager billingTransactionManager() {
  //   JpaTransactionManager transactionManager = new JpaTransactionManager();
  //   transactionManager.setEntityManagerFactory(billingEntityManagerFactory().getObject());
  //   return transactionManager;
  // }
}
