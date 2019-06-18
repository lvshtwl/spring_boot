package com.sqlva.springboot.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Humen implements Serializable {
    private static final long serialVersionUID = -1L;

    private String username;
    private Integer age;

}
