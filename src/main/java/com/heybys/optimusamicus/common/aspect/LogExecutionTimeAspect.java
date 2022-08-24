package com.heybys.optimusamicus.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {
  @Pointcut("this(org.springframework.data.repository.Repository)")
  public void inRepository() {}

  @Around("@within(LogExecutionTime) || @annotation(LogExecutionTime) || inRepository()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    String clazz = joinPoint.getSignature().getDeclaringType().getSimpleName();
    String method = joinPoint.getSignature().getName();
    StopWatch stopWatch = new StopWatch(String.format("[%s::%s]", clazz, method));

    stopWatch.start();
    Object proceed = joinPoint.proceed();
    stopWatch.stop();

    log.info("{} running time = {}s", stopWatch.getId(), stopWatch.getTotalTimeSeconds());

    return proceed;
  }
}
