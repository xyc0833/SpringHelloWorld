package com.book.entity;

public class StudentAOP {
    //这个方法就是我们打算对其进行的增强操作
    public void afterStudy() {
        System.out.println("为什么毕业了他们都继承家产，我还倒给他们打工，我努力的意义在哪里...");
    }
}
