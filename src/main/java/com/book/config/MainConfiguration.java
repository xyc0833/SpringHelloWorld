package com.book.config;

import com.book.entity.ArtTeacher;
import com.book.entity.Student;
import com.book.entity.Teacher;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
//表示这个类是一个 配置类
@Configuration
@ComponentScans({
    @ComponentScan("com.book.entity")
})

public class MainConfiguration {

}
