package com.hcl.hackathon.demo.configuration.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerLogger {
    @Pointcut("execution(* com.hcl.hackathon.demo.controller..*(..))")
    private void logger() {
        throw new UnsupportedOperationException("");
    }

    @Before("logger()")
    public void logEnterMethod(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        if (method != null) {
            log.info("User input for method {}: {}", method.getName(), Arrays.toString(joinPoint.getArgs()));
        }
    }

    private Method getMethod(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                return method;
            }
        }
        return null;
    }
}
