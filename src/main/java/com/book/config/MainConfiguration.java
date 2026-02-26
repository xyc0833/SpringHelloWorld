package com.book.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;

//@EnableAspectJAutoProxy
//表示这个类是一个 配置类
@Configuration
@ComponentScans({
    @ComponentScan("com.book.entity")
})
@MapperScan("com.book.mapper")
public class MainConfiguration {
    //注册SqlSessionTemplate的Bean
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws IOException{
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
//        return new SqlSessionTemplate(factory);
//    }

    //还有不用 xml的方式 直接用注解去配置数据库的连接

    @Bean   //单独创建一个Bean，方便之后更换
    public DataSource dataSource(){
        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/sql_hr", "root", "xuyong612");
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){  //直接参数得到Bean对象
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }
}
