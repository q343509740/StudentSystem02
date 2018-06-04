package com.ray.dao;

import com.ray.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author Ray
 * @date 2018/5/29 0029
 * DAO，即 Date Access Object，数据库访问对象
 * 对数据库相关操作的封装
 */
public interface StudentDao {

    int getTotal(); //查询数据条目
    int getTotalByName(@Param("name") String name); //根据姓名查询数据条目
    void addStudent(Student student); //增加一条数据
    void deleteStudent(int id); //删除一条数据
    void updateStudent(Student student); //更新一条数据
    List<Student> getStudent(@Param("id") int id); //根据id查询一条数据
    List<Student> list(int start, int count); //查询从start位置开始的count条数据
    List<Student> selectStudentByName(@Param("name") String name); //根据name查询数据

    /**
     * 按页查询用户信息
     * @return
     */
    List<Student> findPageStudent();

    /**
     * Mapper接口方式的调用，推荐这种使用方式
     * 这种查询方式需要在xml配置文件中配置配置参数信息
     * @param pageNumKey : 当前页
     * @param pageSizeKey : 每页查询的数量
     * @return : 用户列表信息
     */
    List<Student> findPageByNumAndSize(@Param("pageNumKey") int pageNumKey, @Param("pageSizeKey") int pageSizeKey);

    /**
     采用接口方式的配置RowBounds参数
     * PageRowBounds 是继承 RowBounds的子类，因为RowBounds没有提供查询数量总数，但是PageRowBounds可以获取到
     * @param rowBounds
     * @return
     */
    List<Student> findPageByRowNum(RowBounds rowBounds);

}
