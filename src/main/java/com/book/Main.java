package com.book;

import com.book.config.MainConfiguration;
import com.book.entity.Student;
import com.book.entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        //我们可以为AnnotationConfigApplicationContext指定一个默认的配置类
//        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
//        Student s1 = context.getBean(Student.class);
//        System.out.println(s1.toString());
//
//        System.out.println(context.getBean(Teacher.class));
//        //先构建一个解析器
//        ExpressionParser parser = new SpelExpressionParser();
//        //使用parseExpression方法来创建一个表达式
//        //调用String的toUpperCase方法
//        Expression exp = parser.parseExpression("'Hello World'.toUpperCase()");
//        //表达式最终的运算结果可以通过getValue()获取
//        System.out.println(exp.getValue());
//        Expression exp02 = parser.parseExpression("'Hello World'.bytes");
//        System.out.println(exp02.getValue());
//
//        //读取s1对象的name属性
//        Expression exp03 = parser.parseExpression("name");
//        System.out.println(exp03.getValue(s1));
//
//        exp03.setValue(s1, "刻师傅");
//        System.out.println(s1.toString());
//
//        //由T()囊括，包含完整包名+类名
//        Expression exp04 = parser.parseExpression("T(java.lang.Math).random()");
//        //Expression exp = parser.parseExpression("T(System).nanoTime()");   //默认导入的类可以不加包名
//        System.out.println(exp04.getValue());




        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        Student student = context.getBean(Student.class);
        //先构建一个解析器
        ExpressionParser parser = new SpelExpressionParser();
        //对于Map这里映射型，可以直接使用map[key]来取出value
        Expression exp = parser.parseExpression("map['test']");
        System.out.println(exp.getValue(student));

        Expression exp02 = parser.parseExpression("list[2]");   //对于List、数组这类，可以直接使用[index]
        System.out.println(exp02.getValue(student));

        //也可以快速创建集合
        Expression exp03 = parser.parseExpression("{5, 2, 1, 4, 6, 7, 0, 3, 9, 8}"); //使用{}来快速创建List集合
        List value = (List) exp03.getValue();
        //双冒号代码代表方法引用
        value.forEach(System.out::println);


    }
}
