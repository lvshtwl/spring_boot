package com.sqlva.springboot.repository;

import com.sqlva.springboot.po.MongodbUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<MongodbUser,Long> {
    MongodbUser findByUsername(String username);
}
