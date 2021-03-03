package com.b5wang.javalab.springbootex.web;

import com.b5wang.javalab.springbootex.model.User;
import com.b5wang.javalab.springbootex.service.MySQLDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Rest API 与 HTTP methods:
 * RESTful API 最佳实践 - https://www.ruanyifeng.com/blog/2018/10/restful-api-best-practices.html
 *
 * RESTful API Design: 13 Best Practices to Make Your Users Happy -
 * https://florimond.dev/blog/articles/2018/08/restful-api-design-13-best-practices-to-make-your-users-happy/
 *
 * */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private MySQLDBService mySQLDBService;


    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public String addUser(){
        String uuid = UUID.randomUUID().toString();

        //length 36
        log.info("uuid: {}, length: {}, {}",uuid,uuid.length(),uuid.getBytes().length);

        User user = new User();
        user.setId(uuid);
        user.setUserName(uuid);
        user.setPassword(uuid);
        user.setEmail("test@gmail.com");

        long currentTime = System.currentTimeMillis();
        user.setUpdateTime(currentTime);

        //length 13
        log.info("current time: {}, length: {}",currentTime,String.valueOf(currentTime).length());

        mySQLDBService.createUserInMasterDB(user);
        mySQLDBService.createUserInSlaveDB(user);
        return "OK";
    }



}
