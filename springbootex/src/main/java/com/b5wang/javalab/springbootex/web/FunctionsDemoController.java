package com.b5wang.javalab.springbootex.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionsDemoController {

    @RequestMapping("/")
    public String welcome() {
        return "Hello from Spring Boot!";
    }

}
