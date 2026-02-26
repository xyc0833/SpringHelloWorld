package com.book;


import com.book.config.MainConfiguration;
import com.book.entity.Student;
import com.book.mapper.TestMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        SqlSessionTemplate template = context.getBean(SqlSessionTemplate.class);
        TestMapper testMapper = template.getMapper(TestMapper.class);
        System.out.println(testMapper.getStudent());


    }
}
