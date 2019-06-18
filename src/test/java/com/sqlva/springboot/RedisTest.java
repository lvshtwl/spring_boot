package com.sqlva.springboot;

import com.sqlva.springboot.po.Humen;
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
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Humen> redisTemplate;
    @Test
    public void test() throws Exception {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

    }
    @Test
    public void testHumen(){
        redisTemplate.opsForValue().set("boy",new Humen("男生",28));
        redisTemplate.opsForValue().set("girl",new Humen("女生",18));

        Assert.assertEquals(28, redisTemplate.opsForValue().get("boy").getAge().intValue());
        Assert.assertEquals(18, redisTemplate.opsForValue().get("girl").getAge().intValue());
    }

}
