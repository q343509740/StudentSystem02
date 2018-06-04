package com.ray.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.dao.StudentDao;
import com.ray.entity.Student;
import com.ray.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ray
 * @date 2018/5/29 0029
 * StudentService 的实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public int getTotal() {
        return studentDao.getTotal();
    }

    @Override
    public int getTotalByName(String name) {
        return studentDao.getTotalByName(name);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public boolean deleteStudent(int id) {
        studentDao.deleteStudent(id);
        return true;
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public List<Student> getStudent(int id) {
        List<Student> students = studentDao.getStudent(id);
        return students;
    }

    @Override
    public List<Student> list(int start, int count) {
        return null;
    }

    @Override
    public List<Student> selectByName(String name) {
        List<Student> students = studentDao.selectStudentByName(name);
        return students;
    }

    @Override
    public List<Student> findPageStudent(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Student> students = studentDao.findPageStudent();
        return students;
    }
}
