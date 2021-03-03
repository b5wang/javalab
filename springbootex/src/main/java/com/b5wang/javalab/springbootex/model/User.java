package com.b5wang.javalab.springbootex.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String mobilePhone;
    private Long createTime;
    private Long updateTime;
}
