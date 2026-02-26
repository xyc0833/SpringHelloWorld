package com.book.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
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
@Component//类上直接添加@Component快速注册Bean
@ToString
public class Student {
    public void study(){
        System.out.println("室友还在打游戏，我狠狠的学Java，太爽了");
        //现在我们希望在这个方法执行完之后，打印一些其他的内容，在不修改原有代码的情况下，该怎么做呢？
    }
    public String study(String str){  //现在方法有一个String类型的参数
        System.out.println("都别学Java了，根本找不到工作，快去卷"+str);
        return str;
    }
}
