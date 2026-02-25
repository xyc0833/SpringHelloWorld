package com.book.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//这个类是一个需要被管理的 Bean
//你可以通过 @Autowired 等方式直接注入使用，无需手动 new 对象
@Component
@ToString
public class Student {
    //@Value("${test.name}")
    //public final String name;

//    public Student(@Value("${test.name}") String name){
//        this.name = name;
//    }
    ////比如下面要访问name属性，那么这个属性得可以访问才行，访问权限不够是不行的
//    public String getName(){
//        return name;
//    }
    //@Autowired
    //@Resource
            //@Qualifier("a") //明确指定用a
    //Teacher teacher;

//    public void setTeacher(Teacher teacher){
//        this.teacher = teacher;
//    }

//    @PostConstruct
//    public void init(){
//        System.out.println(name);
//        System.out.println("我是初始化方法");
//    }
//
//    @PreDestroy
//    public void destroy(){
//        System.out.println("我是销毁方法");
//    }


    public Map<String,String> map = Map.of("test","xyc","123","kku");
    public List<String> list = List.of("123","1233321","89789");
}
