package com.sqlva.springboot;

import com.sqlva.springboot.po.Person;
import com.sqlva.springboot.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {
    @Autowired
    private PersonRepository mRepository;

    @Test
    public void testPerson() {
        mRepository.save(new Person("tom1", 18));
        mRepository.save(new Person("tom2", 19));
        mRepository.save(new Person("tom3", 20));
        mRepository.save(new Person("tom4", 21));
        mRepository.save(new Person("tom5", 22));

        // 测试findAll, 查询所有记录
        Assert.assertEquals(5, mRepository.findAll().size());

        // 测试findByName, 查询姓名为FFF的User
        Assert.assertEquals(18, mRepository.findByName("tom1").getAge());

        // 测试findUser, 查询姓名为FFF的User
        Assert.assertEquals(19, mRepository.findUser("tom2").getAge());

        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
        Assert.assertEquals("tom3", mRepository.findByNameAndAge("tom3", 20).getName());

        // 测试删除姓名为AAA的User
        mRepository.delete(mRepository.findByName("tom4"));

        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals(4, mRepository.findAll().size());
    }
}
