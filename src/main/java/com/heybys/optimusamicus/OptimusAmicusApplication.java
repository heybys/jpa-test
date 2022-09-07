package com.heybys.optimusamicus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class OptimusAmicusApplication {

  public static void main(String[] args) {
    SpringApplication.run(OptimusAmicusApplication.class, args);
  }
}
