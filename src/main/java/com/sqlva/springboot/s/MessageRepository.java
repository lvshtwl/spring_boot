package com.sqlva.springboot.s;

import com.sqlva.springboot.s.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {

}
