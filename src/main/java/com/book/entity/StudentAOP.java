package com.book.entity;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class StudentAOP implements MethodInterceptor {
    @Override
    //invoke方法就是代理方法
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //跟之前一样，需要手动proceed()才能调用原方法
        Object value = invocation.proceed();
        return value+"增强";
    }

//    @Override
//    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
//        System.out.println("我是前置方法");
//    }
}
