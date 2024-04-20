package com.ra.web.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("within(com.ra.web.service.Impl.*)")
    public void logForService() {}

    @Around("logForService()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // Các xử lý trước khi thực thi
        LOGGER.info("[START] {}.{} with params {}", joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(),
                Arrays.asList(joinPoint.getArgs()));

        try { // Thực thi
            Long strat = System.currentTimeMillis();
            Object result = joinPoint.proceed(); // cho phương thức thực thi
            Long totalTimes = System.currentTimeMillis() - strat;
            // Sau khi thực thi
            LOGGER.info("[END] {}.{} with return {}",
                    joinPoint.getSignature().getDeclaringType(),
                    joinPoint.getSignature().getName(),
                    result);
            LOGGER.info("[LOG_ASPECT] {}.{} executed in {}ms",
                    joinPoint.getSignature().getDeclaringType(),
                    joinPoint.getSignature().getName(),
                    totalTimes);
            return result;
        } catch (Exception ex) {
            // Khi xảy ra exception
            LOGGER.error("[EXCEPTION]  {}.{} exception {}",
                    joinPoint.getSignature().getDeclaringType(),
                    joinPoint.getSignature().getName(),
                    ex.getMessage());
            throw ex;
        }
    }
}
