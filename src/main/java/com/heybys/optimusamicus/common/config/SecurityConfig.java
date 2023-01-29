package com.heybys.optimusamicus.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/static/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .mvcMatchers("/", "/info", "/info2", "/member/**").permitAll()
        .mvcMatchers("/admin").hasRole("ADMIN")
        .anyRequest().authenticated();
    http.formLogin();
    // http.httpBasic();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //   auth.inMemoryAuthentication()
  //       .withUser("user").password(passwordEncoder().encode("123")).roles("NORMAL").and()
  //       .withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN");
  // }

  // @Bean
  // public CorsConfigurationSource corsConfigurationSource() {
  //   CorsConfiguration corsConfiguration = new CorsConfiguration();
  //
  //   corsConfiguration.addAllowedOriginPattern("*");
  //   corsConfiguration.addAllowedHeader("*");
  //   corsConfiguration.addAllowedMethod("*");
  //   corsConfiguration.setAllowCredentials(true);
  //
  //   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  //   source.registerCorsConfiguration("/**", corsConfiguration);
  //   return source;
  // }
}
