package com.heybys.jpatest.sub.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubDataSourceConfig {

  @Bean("subHikariConfig")
  @ConfigurationProperties(prefix="spring.datasource.hikari.sub")
  public HikariConfig subHikariConfig() {
    return new HikariConfig();
  }

  @Bean("subDataSource")
  public HikariDataSource subDataSource() {
    return new HikariDataSource( subHikariConfig());
  }

}
