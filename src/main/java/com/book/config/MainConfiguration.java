package com.book.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.*;

import java.io.IOException;

@EnableAspectJAutoProxy
//表示这个类是一个 配置类
@Configuration
@ComponentScans({
    @ComponentScan("com.book.entity")
})

public class MainConfiguration {
    //注册SqlSessionTemplate的Bean
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws IOException{
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        return new SqlSessionTemplate(factory);
    }
}
