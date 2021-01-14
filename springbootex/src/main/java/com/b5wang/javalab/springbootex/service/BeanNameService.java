package com.b5wang.javalab.springbootex.service;

import org.springframework.stereotype.Service;

public class BeanNameService {

    private String name;

    public BeanNameService(String name){
        this.name = name;
    }

    public String hello(){
        return "You got the bean[" + name + "]!~";
    }

}
