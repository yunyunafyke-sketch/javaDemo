package com.afyke.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    /**
     * 切入点：
     * 拦截 com.afyke.demo.service 包下所有类的所有方法
     */
    @Pointcut("execution(* com.afyke.demo.service.*.*(..))")
    public void serviceMethods() {
    }

    /**
     * 环绕通知：
     * 方法执行前、执行后都能处理
     */
    @Around("serviceMethods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("AOP 方法执行前：" + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        System.out.println("AOP 方法执行后：" + joinPoint.getSignature().getName());

        return result;
    }

}
