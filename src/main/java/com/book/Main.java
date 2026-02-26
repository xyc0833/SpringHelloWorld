package com.book;

import com.book.config.MainConfiguration;
import com.book.entity.Student;
import com.book.entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Student bean = context.getBean(Student.class);
        bean.study("AAAA");
        //System.out.println("已报名："+bean.study("Java"));
        System.out.println(bean.getClass());


    }
}
