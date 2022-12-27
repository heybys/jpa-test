package com.heybys.optimusamicus.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationEventPublisherProvider implements ApplicationEventPublisherAware {

  private ApplicationEventPublisher publisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    publisher = applicationEventPublisher;
  }

  public ApplicationEventPublisher getPublisher() {
    return publisher;
  }

  public void publishEvent(Object event) {
    if (publisher != null) {
      log.debug("publish event. {}", event);
      publisher.publishEvent(event);
    }
  }
}
