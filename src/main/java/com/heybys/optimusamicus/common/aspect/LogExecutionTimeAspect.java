package com.heybys.optimusamicus.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogExecutionTimeAspect {

  Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

  @Around("@annotation(LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch(joinPoint.getSignature().getName());
    stopWatch.start();

    Object proceed = joinPoint.proceed();

    stopWatch.stop();
    logger.info("StopWatch '{}': running time = {} s", stopWatch.getId(),
        stopWatch.getTotalTimeSeconds());

    return proceed;
  }
}
