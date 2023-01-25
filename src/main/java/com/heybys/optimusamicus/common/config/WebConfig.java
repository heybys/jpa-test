package com.heybys.optimusamicus.common.config;

import com.heybys.optimusamicus.common.filter.AuthFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final AuthFactory authFactory;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // registry
    //     .addMapping("/**")
    //     .allowedMethods("*")
    //     .allowedOrigins("http://localhost:3000")
    //     .allowedHeaders("*")
    //     .allowCredentials(true);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // not set yet.
  }
}
