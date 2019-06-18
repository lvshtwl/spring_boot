package com.sqlva.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @RequestMapping(value = "/helloworld",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "Hello World" + name;
    }
}
