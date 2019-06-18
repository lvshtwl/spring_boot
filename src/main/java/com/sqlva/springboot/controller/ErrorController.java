package com.sqlva.springboot.controller;

import com.sqlva.springboot.config.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}
