package com.example.WaaLab3.aspect;

import com.example.WaaLab3.repositories.LoggerRepo;
import com.example.WaaLab3.models.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;



@Component
@Aspect
public class LoggerAspect {
    @Autowired
    LoggerRepo loggerRepo;


    @Pointcut("@annotation(com.example.WaaLab3.aspect.ExecutionTime)")
    public void executionTimePointCut() {
    }

    @Around("executionTimePointCut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        Logger logger = new Logger();
        logger.setDate(LocalDate.now());
        logger.setTime(LocalTime.now());
        logger.setPrinciple("user1");
        logger.setOperation(joinPoint.getSignature().toShortString() + " executed in " + executionTime + "ms");

        loggerRepo.save(logger);

        return proceed;


    }
}
