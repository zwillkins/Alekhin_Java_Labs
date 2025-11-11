package ru.lab6.demo.aspect;

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
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * ru.lab6.demo.controller.UserController.*(..))")
    public void userControllerMethods() {}

    @Around("userControllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        log.info("==> Вход в метод: {}({})", methodName, Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("<== Ошибка в методе: {}. Причина: {}", methodName, throwable.getMessage());
            throw throwable;
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("<== Выход из метода: {}(). Результат: {}. Время выполнения: {} мс", methodName, result, executionTime);

        return result;
    }
}