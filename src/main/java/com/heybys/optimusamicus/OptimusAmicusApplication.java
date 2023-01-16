package com.heybys.optimusamicus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

// @ServletComponentScan
@EnableAsync
@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class OptimusAmicusApplication {

  public static void main(String[] args) {
    SpringApplication.run(OptimusAmicusApplication.class, args);
  }
}
