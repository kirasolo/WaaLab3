package com.example.WaaLab3.aspect;

import com.example.WaaLab3.repositories.LoggerRepo;
import com.example.WaaLab3.aspect.LoggerEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class ExecutionTimeAspect {
    @Autowired
    private LoggerRepo loggerRepo;

    @Around("@annotation(com.example.WaaLab3.aspect.ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        Date now = new Date();
        String operation = joinPoint.getSignature().toShortString();
        LoggerEntity logger = new LoggerEntity(null, now, now, "kira", operation + " executed in " + executionTime + "ms");
        loggerRepo.save(logger);

        return proceed;

    }
}
