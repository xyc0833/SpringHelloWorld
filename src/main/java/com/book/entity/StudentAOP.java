package com.book.entity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class StudentAOP {
    //这个方法就是我们打算对其进行的增强操作
    //JoinPoint实例会被自动传入
    //JoinPoint参数，通过此参数就可以快速获取切点位置的一些信息：
    public void afterStudy(JoinPoint joinPoint) {
        System.out.println("为什么毕业了他们都继承家产，我还倒给他们打工，我努力的意义在哪里...");
        //这个str参数我们该从哪里拿呢？
        System.out.println("学什么"+joinPoint.getArgs()[0]+"，Rust天下第一！");
    }

    //ProceedingJoinPoint 是 Spring AOP 中专用于环绕通知（@Around） 的核心接口，
    // 它的本质是：封装了被拦截的目标方法的所有信息，并提供了执行目标方法的核心能力。
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("我是她的家长，他不能学Java，必须学Rust，这是为他好");
        Object value = joinPoint.proceed(new Object[]{"Rust"});
        if(value.equals("Java")) {
            System.out.println("听话，学Rust以后进大厂！");
            value = "Rust";
        }
        return value;
    }
}
