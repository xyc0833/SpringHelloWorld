package com.book;

//import com.book.entity.Student;
import com.book.DI.Student;
import com.book.DI.StudentFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //Bean:一个由Spring IoC容器实例化、组装和管理的对象，我们称其为Bean
        //IoC容器用于管理Bean
        //如果要用IoC容器 则需要一个应用程序上下文
        //它代表的就是IoC容器 它会负责实例化、配置和组装Bean
        //ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        //ApplicationContext是应用程序上下文的顶层接口，它有很多种实现，这里我们先介绍第一种
        //因为这里使用的是XML配置文件，所以说我们就使用 ClassPathXmlApplicationContext 这个实现类

        //现在，这个对象不需要我们再去创建了，而是由IoC容器自动进行创建并提供，
        //我们可以直接从上下文中获取到它为我们创建的对象
        //使用getBean方法来获取对应的对象（Bean）
        //Student student = context.getBean(Student.class);
        //等于我们自己没有创建对象 通过ioc容器 去new了一个对象
        //System.out.println(student);

        //Student s1 = (Student) context.getBean("student");
        //System.out.println(s1);

        //IoC容器创建的Bean是每次都只有一个 单例模式
        //Student student1 = context.getBean(Student.class);
        //Student student2 = context.getBean(Student.class);
        //System.out.println(student1 == student2);   //默认为单例模式，对象始终为同一个


        // 依赖注入
        //Student student = context.getBean(Student.class);
        //student.study();
        //student.toString();

        //student.printlist();


        //创建一个ioc容器
        //这里不是Application而是 ClassPathXmlApplicationContext
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        //当容器创建时，默认情况下Bean都是单例的，那么都会在一开始就加载好，对象构造完成后，会执行init-method
        //我们可以调用close方法关闭容器，此时容器内存放的Bean也会被一起销毁，会执行destroy-method
        System.out.println(context.getBean(Student.class));
        System.out.println(context.getBean(StudentFactory.class));
        //Student bean = (Student) context.getBean("studentFactory");

        //如果通过名字获取 工厂bean 会直接返回一个 student(工厂对应生成的类)
        System.out.println(context.getBean("studentFactory"));

        //如果获得 工厂bean本身 添加 “&”符号
        System.out.println(context.getBean("&studentFactory"));
        context.close();

    }
}