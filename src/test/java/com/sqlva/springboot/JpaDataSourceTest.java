package com.sqlva.springboot;

import com.sqlva.springboot.p.Pig;
import com.sqlva.springboot.s.MessageRepository;
import com.sqlva.springboot.p.PigRepository;
import com.sqlva.springboot.s.Message;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaDataSourceTest {
    @Autowired
    private PigRepository pigRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Test
//    @Transactional(isolation = Isolation.DEFAULT)
    public void testJpa(){
        pigRepository.save(new Pig("aaaa",11));
        pigRepository.save(new Pig("bbbb",12));
        pigRepository.save(new Pig("ccccc",13));
        pigRepository.save(new Pig("dddd",14));

        Assert.assertEquals(4, pigRepository.findAll().size());


        messageRepository.save(new Message("aaaaaa","ccccccc"));
        messageRepository.save(new Message("aaaaaa","ccccccc"));
        messageRepository.save(new Message("aaaaaa","ccccccc"));
        messageRepository.save(new Message("aaaaaa","ccccccc"));
        messageRepository.save(new Message("aaaaaa","ccccccc"));
        messageRepository.save(new Message("aaaaaa","ccccccc"));
        Assert.assertEquals(6, messageRepository.findAll().size());

    }
}
