package com.sqlva.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataSourceTest {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected  JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    protected  JdbcTemplate secondJdbcTemplate;

    @Before
    public void setup() {
        primaryJdbcTemplate.update("delete from student");
        secondJdbcTemplate.update("delete from student");
    }

    @Test
    public void test() {
        // 往第一个数据源中插入两条数据
        primaryJdbcTemplate.update("insert  into  student(name ,age) values (?,?)","tom",18);
        secondJdbcTemplate.update("insert  into  student(name ,age) values (?,?)","tom",19);
    }
}
