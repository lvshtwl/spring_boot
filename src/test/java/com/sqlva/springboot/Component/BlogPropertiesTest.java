package com.sqlva.springboot.Component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogPropertiesTest {
    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void test(){
        Assert.assertEquals(blogProperties.getName(), "吕少强");
        Assert.assertEquals(blogProperties.getTitle(), "springboot");
    }
}