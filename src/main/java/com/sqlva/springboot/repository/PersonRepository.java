package com.sqlva.springboot.repository;

import com.sqlva.springboot.po.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    //没有任何类SQL语句就完成了两个条件查询方法。这就是Spring-data-jpa的一大特性：通过解析方法名创建查询。
    public Person findByName(String name);

    Person findByNameAndAge(String name, Integer age);
    //除了通过解析方法名来创建查询外，它也提供通过使用@Query 注解来创建查询，您只需要编写JPQL语句，并通过类似“:name”来映射@Param指定的参数
    @Query("from Person p where p.name=:name")
    Person findUser(@Param("name") String name);

}
