package com.sqlva.springboot.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongodbUser {
    @Id
    private Long id;

    private String username;
    private Integer age;
}
