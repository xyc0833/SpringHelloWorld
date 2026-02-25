package com.book.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//这个类是一个需要被管理的 Bean
//你可以通过 @Autowired 等方式直接注入使用，无需手动 new 对象
@Component
@ToString
public class Student {
    //@Autowired
    @Resource
            //@Qualifier("a") //明确指定用a
    Teacher teacher;

//    public void setTeacher(Teacher teacher){
//        this.teacher = teacher;
//    }

    @PostConstruct
    public void init(){
        System.out.println("我是初始化方法");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("我是销毁方法");
    }
}
