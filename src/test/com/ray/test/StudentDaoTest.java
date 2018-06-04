package com.ray.test;

import com.ray.dao.StudentDao;
import com.ray.entity.Student;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author Ray
 * @date 2018/5/29 0029
 */
public class StudentDaoTest extends BaseJunit4Test {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentDao studentDao;

    @Test
    public void getTotal(){
        int count;
        count = studentDao.getTotal();
        System.out.println(count);
    }

    @Test
    public void addStudent(){
        Student student = new Student();
        for(int i = 56; i < 57; i++){
            student.setStudent_id(i);
            student.setName("Ray" + i);
            student.setAge(17 + i);
            student.setSex("男");
            student.setBirthday(new Date());
            studentDao.addStudent(student);
        }
    }

    @Test
    public void getStudent(){
        List<Student> students = studentDao.getStudent(4);
        for (Student student :
                students) {
            System.out.println(student.getAge());
            System.out.println(student.getName());
        }
    }
}
