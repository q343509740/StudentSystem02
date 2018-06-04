package com.ray.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import com.ray.dao.StudentDao;
import com.ray.entity.Student;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Ray
 * @date 2018/6/2 0002
 */
public class PageHelperTest extends BaseJunit4Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageHelperTest.class);

    @Autowired
    private StudentDao studentDao;

    /**
     * 方式一
     * Mapper接口方式的调用
     * 采用PageHelper.startPage(1, 10);方式调用
     * 使用方式如下
     */
    @Test
    public void testStartPage(){
        /*
            startPage方法的第一个参数为页码，第二个参数为每页显示数量
            查询语句必须紧跟着PageHelper.startPage(2, 1)后面，否则会报错
         */
        PageHelper.startPage(2,5);
        List<Student> pageStudent = studentDao.findPageStudent();
        for(Student student:pageStudent){
            LOGGER.info("============={}",student);
        }
        LOGGER.info("查询数量为count={},pageStudent={}",pageStudent.size(),pageStudent);
    }

    /**
     * 方式二
     * Mapper接口方式的调用
     * 采用PageHelper.offsetPage(1, 10);方式调用
     * 使用方式如下
     */
    @Test
    public void testOffsetPag(){
        /*
            offsetPage方法的第一个参数为页码，第二个参数为每页显示数量
            查询语句必须紧跟着PageHelper.offsetPage(2, 1)后面
            注意offsetPage 方法的第一页数据的当前页为0不是1，这点和startPage不同
         */
        PageHelper.offsetPage(0,1);
        List<Student> pageStudent = studentDao.findPageStudent();
        for (Student student :
                pageStudent) {
            LOGGER.info("============={}",student);
        }
        LOGGER.info("查询数量为count={},pageStudent={}",pageStudent.size(),pageStudent);
    }

    /**
     * 方式三 参数方法调用
     * Mapper接口中存在类似于
     * List<User> findPageByNumAndSize(@Param("pageNumKey") int pageNumKey, @Param("pageSizeKey") int pageSizeKey);
     * 的方法，只需要在xml配置文件中配置supportMethodsArguments=true即可,
     * 官网说还需要配置<property name="params" value="pageNum=pageNumKey;pageSize=pageSizeKey;"/>,
     * 但是我这里测试了一下，配置和不配置都是可以的，如果配置，注意参数的问题，要对应上
     * 使用方式如下
     */
    @Test
    public void testParmerPage(){
        List<Student> pageStudent = studentDao.findPageByNumAndSize(2,2);
        for (Student student :
                pageStudent) {
            LOGGER.info("=============={}",student);
        }
        LOGGER.info("查询数量为count={},pageUser={}",pageStudent.size(),pageStudent);
    }

    /**
     * 方式四  RowBounds方式分页
     * 使用方式如下
     */
    @Test
    public void testRowNumPage(){
        PageRowBounds rowBounds = new PageRowBounds(2,3);
        List<Student> pageStudent = studentDao.findPageByRowNum(rowBounds);
        for (Student student :
                pageStudent) {
            LOGGER.info("============{}",student);
        }
        LOGGER.info("查询数量为count={},pageStudent={}",pageStudent.size(),pageStudent);
    }

    /**
     * 方式五  PageInfo方式
     * 这种方式不属于分页，是一种封装分页的方式，
     * 就是说，将已经分页查询之后的数据进行再一次的封装，这样会获取到非常全面的分页属性
     * 使用方式如下
     */
    @Test
    public void testPageInfo(){
        List<Student> pageStudent = studentDao.findPageByNumAndSize(1,2);
        PageInfo pageInfo = new PageInfo(pageStudent);
        LOGGER.info("pageNum当前页={}",pageInfo.getPageNum());
        LOGGER.info("pageSize每页的数量={}",pageInfo.getPageSize());
        LOGGER.info("size当前页的数量={}",pageInfo.getSize());
        LOGGER.info("startRow当前页面第一个元素在数据库中的行号={}",pageInfo.getStartRow());
        LOGGER.info("endRow当前页面最后一个元素在数据库中的行号={}",pageInfo.getEndRow());
        LOGGER.info("total总记录数(在这里也就是查询到的用户总数)={}",pageInfo.getTotal());
        LOGGER.info("pages总页数={}",pageInfo.getPages());
        LOGGER.info("list结果集={}",pageInfo.getList());
        LOGGER.info("prePage前一页={}",pageInfo.getPrePage());
        LOGGER.info("nextPage下一页={}",pageInfo.getNextPage());
        LOGGER.info("isFirstPage是否为第一页={}",pageInfo.isIsFirstPage());
        LOGGER.info("isLastPage是否为最后一页={}",pageInfo.isIsLastPage());
        LOGGER.info("hasPreviousPage是否有前一页={}",pageInfo.isHasPreviousPage());
        LOGGER.info("hasNextPage是否有下一页={}",pageInfo.isHasNextPage());

    }
}
