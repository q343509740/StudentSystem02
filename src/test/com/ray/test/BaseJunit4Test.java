package com.ray.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Ray
 * @date 2018/5/29 0029
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用Spring Junit4进行测试
@ContextConfiguration({"classpath:spring/*.xml"}) //加载配置文件
public class BaseJunit4Test {
}
