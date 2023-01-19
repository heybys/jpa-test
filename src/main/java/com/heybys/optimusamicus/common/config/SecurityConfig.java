package com.heybys.optimusamicus.common.config;

import com.heybys.optimusamicus.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final MemberService memberService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // http.cors().configurationSource(this.corsConfigurationSource()).and()
    //     .authorizeRequests()
    //     .mvcMatchers(HttpMethod.POST, "api/v1/login", "api/v1/member").permitAll()
    //     .mvcMatchers(HttpMethod.DELETE, "api/v1/logout").permitAll()
    //     .mvcMatchers(HttpMethod.GET, "api/v1/shop/menu-board").authenticated()
    //     .anyRequest().permitAll();
    // http.httpBasic();
    //
    http.csrf().disable()
        .cors().configurationSource(this.corsConfigurationSource()).and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/v1/member").permitAll()
        .anyRequest().permitAll().and()
        .httpBasic();
  }

  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    // you can configure many allowed CORS headers
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //   auth.userDetailsService(memberService);
  // }
}
