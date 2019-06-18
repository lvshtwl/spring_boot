package com.sqlva.springboot;

import com.sqlva.springboot.po.Humen;
import com.sqlva.springboot.po.MongodbUser;
import com.sqlva.springboot.repository.MongoUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {
    @Autowired
    MongoUserRepository mongoUserRepository;
    @Test
    public void test(){
        mongoUserRepository.save(new MongodbUser(1l,"qiang1",11));
        mongoUserRepository.save(new MongodbUser(2l,"qiang2",12));
        mongoUserRepository.save(new MongodbUser(3l,"qiang3",13));

        Assert.assertEquals(3, mongoUserRepository.findAll().size());

        // 删除一个User，再验证User总数
        MongodbUser user = mongoUserRepository.findByUsername("qiang1");
        mongoUserRepository.delete(user);
        Assert.assertEquals(2, mongoUserRepository.findAll().size());
    }

}
