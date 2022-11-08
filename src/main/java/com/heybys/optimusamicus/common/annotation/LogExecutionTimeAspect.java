package com.heybys.optimusamicus.common.annotation;

import java.text.NumberFormat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {

  @Value("${app.logging.execution_time.enable}")
  private boolean enableLogging;

  @Pointcut("this(org.springframework.data.repository.Repository)")
  public void inRepository() {}

  @Around(
      "@within(com.heybys.optimusamicus.common.annotation.LogExecutionTime) || @annotation(com.heybys.optimusamicus.common.annotation.LogExecutionTime) || inRepository()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Object proceed;

    String clazz = joinPoint.getSignature().getDeclaringType().getSimpleName();
    String method = joinPoint.getSignature().getName();
    StopWatch stopWatch = new StopWatch(String.format("[%s::%s]", clazz, method));

    stopWatch.start();
    try {
      proceed = joinPoint.proceed();
    } finally {
      stopWatch.stop();

      NumberFormat numberFormat = NumberFormat.getInstance();
      numberFormat.setGroupingUsed(false);
      numberFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
      numberFormat.setMaximumIntegerDigits(Integer.MAX_VALUE);

      if (enableLogging) {
        log.info(
            "{} Executed in {}s",
            stopWatch.getId(),
            numberFormat.format(stopWatch.getTotalTimeSeconds()));
      }
    }

    return proceed;
  }
}
