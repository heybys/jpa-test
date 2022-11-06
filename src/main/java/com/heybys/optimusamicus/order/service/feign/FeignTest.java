package com.heybys.optimusamicus.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "hello", url = "https://naver.com")
public interface FeignTest {

  @GetMapping(path = "/articles")
  Object getArticles();
}
