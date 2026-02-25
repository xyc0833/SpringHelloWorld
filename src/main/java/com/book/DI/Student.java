package com.book.DI;

public class Student {
//    private Teacher teacher = new ArtTeacher();
//    //在以前，如果我们需要制定哪个老师教我们，直接new创建对应的对象就可以了
    public void study(){
        teacher.teach();
    }

    //现在我们不需要去new了 因为在实际写代码的情况下 new的话 有可能牵一发而动全身
    private Teacher teacher;
    //依赖注入要求对应的属性必须有一个set方法

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
