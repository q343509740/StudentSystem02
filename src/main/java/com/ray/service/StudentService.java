package com.ray.service;

import com.github.pagehelper.PageInfo;
import com.ray.entity.Student;

import java.util.List;

/**
 * @author Ray
 * @date 2018/5/29 0029
 * Service 层我们可以增加一些非 CRUD 的方法去更好的完成本身抽离出来的 service 服务
 */
public interface StudentService {

    int getTotal();
    int getTotalByName(String name);
    void addStudent(Student student);
    boolean deleteStudent(int id);
    void updateStudent(Student student);
    List<Student> getStudent(int id);
    List<Student> list(int start, int count);
    List<Student> selectByName(String name);

    List<Student> findPageStudent(int page, int rows);
}