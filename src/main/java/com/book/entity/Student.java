package com.book.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//这个类是一个需要被管理的 Bean
//你可以通过 @Autowired 等方式直接注入使用，无需手动 new 对象
//@Component//类上直接添加@Component快速注册Bean
@Data
public class Student {
    private int sid;
    private String name;
    private String sex;


}
