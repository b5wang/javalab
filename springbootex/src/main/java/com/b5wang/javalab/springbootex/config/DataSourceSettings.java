package com.b5wang.javalab.springbootex.config;

import lombok.Data;

@Data
public class DataSourceSettings {
    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private String initializationMode;
    private DataSourceHikariSettings hikari;
}
