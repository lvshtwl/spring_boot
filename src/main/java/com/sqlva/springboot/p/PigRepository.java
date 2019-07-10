package com.sqlva.springboot.p;

import com.sqlva.springboot.p.Pig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
@CacheConfig(cacheNames = "pig")
public interface PigRepository extends JpaRepository<Pig,Integer> {
    @Cacheable
    Pig findByName(String name);
}
