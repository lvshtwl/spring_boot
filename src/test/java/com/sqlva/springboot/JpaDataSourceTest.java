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

@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaDataSourceTest {
    @Autowired
    private PigRepository pigRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Test
    public void testJpa(){
        pigRepository.save(new Pig("a",11));
        pigRepository.save(new Pig("b",12));
        pigRepository.save(new Pig("c",13));
        pigRepository.save(new Pig("d",14));

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
