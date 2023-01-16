package com.heybys.optimusamicus.common.config;

import com.heybys.optimusamicus.common.filter.AuthFactory;
import com.heybys.optimusamicus.user.service.AuthService;
import javax.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final AuthFactory authFactory;

  @Bean
  public AuthService authService() {
    return authFactory.getService();
  }

  @Bean
  public FilterRegistrationBean<Filter> authFilter() {
    Filter authFilter = authFactory.getFilter();

    FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(authFilter);
    filterFilterRegistrationBean.addUrlPatterns("/*");
    return filterFilterRegistrationBean;
  }
}
