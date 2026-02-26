package com.book;


import com.book.config.MainConfiguration;
import com.book.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        Student bean = context.getBean(Student.class);
        bean.study();

    }
}
