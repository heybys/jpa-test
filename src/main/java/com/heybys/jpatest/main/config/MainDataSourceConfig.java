package com.heybys.jpatest.main.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MainDataSourceConfig {

  @Bean("mainHikariConfig")
  @Primary
  @ConfigurationProperties(prefix="spring.datasource.hikari.main")
  public HikariConfig mainHikariConfig() {
    return new HikariConfig();
  }

  @Bean("mainDataSource")
  @Primary
  public HikariDataSource mainDataSource() {
    return new HikariDataSource( mainHikariConfig());
  }

}
