package com.book;


import com.book.config.MainConfiguration;
import com.book.entity.Student;
import com.book.mapper.TestMapper;
import com.book.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("项目正在启动...");
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        TestMapper testMapper = context.getBean(TestMapper.class);
        System.out.println(testMapper.getStudent());

        TestService testService = context.getBean(TestService.class);
        testService.test();

    }
}
