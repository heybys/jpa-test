package com.heybys.optimusamicus.delivery.config;

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
//     basePackages = "com.heybys.optimusamicus.delivery.repository",
//     entityManagerFactoryRef = "deliveryEntityManagerFactory",
//     transactionManagerRef = "deliveryTransactionManager")
public class DeliveryDataSourceConfig {

  // private final CommonConfigFactory commonConfigFactory;
  //
  // @Value("${app.domain.delivery.persistence-unit-name}")
  // private String persistenceUnitName;
  //
  // @Value("${spring.datasource.hikari.delivery.jdbc-url}")
  // private String jdbcUrl;
  //
  // @Value("${spring.datasource.hikari.delivery.username}")
  // private String username;
  //
  // @Value("${spring.datasource.hikari.delivery.password}")
  // private String password;
  //
  // @Bean
  // public HikariConfig deliveryHikariConfig() {
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
  // public DataSource deliveryDataSource() {
  //   return new HikariDataSource(deliveryHikariConfig());
  // }
  //
  // @Bean
  // public JdbcTemplate deliveryJdbcTemplate() {
  //   return new JdbcTemplate(deliveryDataSource());
  // }
  //
  // @Bean
  // public LocalContainerEntityManagerFactoryBean deliveryEntityManagerFactory() {
  //   LocalContainerEntityManagerFactoryBean factoryBean =
  //       commonConfigFactory.createEntityManagerFactoryBean();
  //   factoryBean.setDataSource(deliveryDataSource());
  //   factoryBean.setPersistenceUnitName(persistenceUnitName);
  //   factoryBean.setPackagesToScan("com.heybys.optimusamicus.delivery.entity");
  //   return factoryBean;
  // }
  //
  // @Bean
  // public PlatformTransactionManager deliveryTransactionManager() {
  //   JpaTransactionManager transactionManager = new JpaTransactionManager();
  //   transactionManager.setEntityManagerFactory(deliveryEntityManagerFactory().getObject());
  //   return transactionManager;
  // }
}
