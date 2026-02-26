package com.book.mapper;

import com.book.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {
    @Select("select * from student where sid = 1001")
    Student getStudent();

    @Insert("insert into student(sid,name, sex) values(1,'测试', '男')")
    void insertStudent();
}
