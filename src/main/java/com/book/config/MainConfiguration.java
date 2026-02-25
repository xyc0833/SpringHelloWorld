package com.book.config;

import com.book.entity.ArtTeacher;
import com.book.entity.Student;
import com.book.entity.Teacher;
import org.springframework.context.annotation.*;

//表示这个类是一个 配置类
@Configuration
@ComponentScan("com.book.entity")
public class MainConfiguration {
    //如何配置Bean
    //@Bean("student")
    //@Bean(name = "",initMethod = "",destroyMethod = "",autowireCandidate = false)
    //autowireCandidate = false 的作用是：把这个 Bean 标记为 “不参与自动注入”，
    // Spring 在做 @Autowired 自动匹配时，会直接忽略这个 Bean。
    //@Bean
    //@Lazy(true)     //对应lazy-init属性
    //@Scope("prototype")    //对应scope属性
    //@DependsOn("teacher")    //对应depends-on属性
    //@DependsOn（或 depends-on 属性）的核心作用是强制指定 Bean 的初始化顺序
//    public Student student(){
//        return new Student();
//    }

//    @Bean
//    public Teacher teacher(){
//        return new ArtTeacher();
//    }
//
//    @Bean
//    public Student student(){
//        Student student = new Student();
////        student.setTeacher(teacher);
//        return student;
//    }

//    @Bean("a")
//    public Teacher teacherA(){
//        return new ArtTeacher();
//    }
//
//    @Bean("b")
//    public Teacher teacherB(){
//        return new ArtTeacher();
//    }


}
