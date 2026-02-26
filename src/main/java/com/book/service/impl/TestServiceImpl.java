package com.book.service.impl;

import com.book.mapper.TestMapper;
import com.book.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestServiceImpl implements TestService {

    @Resource
    TestMapper mapper;

    @Transactional   //此注解表示事务，之后执行的所有方法都会在同一个事务中执行
    public void test() {
        mapper.insertStudent();
        if(true) throw new RuntimeException("我是测试异常！");
        mapper.insertStudent();
    }
}
