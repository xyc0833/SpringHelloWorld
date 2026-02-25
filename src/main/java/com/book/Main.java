package com.book;

import com.book.config.MainConfiguration;
import com.book.entity.Student;
import com.book.entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //我们可以为AnnotationConfigApplicationContext指定一个默认的配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        Student s1 = context.getBean(Student.class);
        System.out.println(s1.toString());

        System.out.println(context.getBean(Teacher.class));

    }
}
