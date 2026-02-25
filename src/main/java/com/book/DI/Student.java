package com.book.DI;

import lombok.ToString;

import java.sql.SQLOutput;
import java.util.List;

@ToString
public class Student {
    private Teacher teacher;
    public Student(){
        System.out.println("学生构造方法");
    }
    public void setTeacher(Teacher teacher){
        teacher.teach();
    }
    //针对IO场景可能使用较多
    public void init(){
        System.out.println("我是对象初始化时要做的事情！");
    }

    public void destroy(){
        System.out.println("我是对象销毁时要做的事情！");
    }

}
