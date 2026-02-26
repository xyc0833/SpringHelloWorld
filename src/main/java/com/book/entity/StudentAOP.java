package com.book.entity;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
//添加@Aspect注解和@Component将其注册为Bean即可
public class StudentAOP {
    //execution写法跟之前一样
    @Before("execution(* com.book.entity.Student.study())")
    public void before(){
        System.out.println("我是之前执行的内容！");
    }

}
