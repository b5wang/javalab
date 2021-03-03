package com.b5wang.javalab.springbootex.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean("masterDataSourceSettings")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceSettings masterDataSourceSettings(){
        return new DataSourceSettings();
    }

    @Bean("slaveDataSourceSettings")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSourceSettings slaveDataSourceSettings(){
        return new DataSourceSettings();
    }

    //1
//    @Bean("masterDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource masterDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean("slaveDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    public DataSource slaveDataSource(){
//        return DataSourceBuilder.create().build();
//    }

    //2
//    @Bean("masterDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource masterDataSource(DataSourceProperties dataSourceProperties){
//        return DataSourceBuilder
//                .create(dataSourceProperties.getClassLoader())
//                .driverClassName(dataSourceProperties.getDriverClassName())
//                .url(dataSourceProperties.getUrl())
//                .username(dataSourceProperties.getUsername())
//                .password(dataSourceProperties.getPassword())
//                .build();
//    }
//
//    @Bean("slaveDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    public DataSource slaveDataSource(DataSourceProperties dataSourceProperties){
//        return DataSourceBuilder
//                .create(dataSourceProperties.getClassLoader())
//                .driverClassName(dataSourceProperties.getDriverClassName())
//                .url(dataSourceProperties.getUrl())
//                .username(dataSourceProperties.getUsername())
//                .password(dataSourceProperties.getPassword())
//                .build();
//    }

    //3
    public HikariDataSource createHikariDataSource(DataSourceSettings dataSourceSettings){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dataSourceSettings.getDriverClassName());
        hikariConfig.setJdbcUrl(dataSourceSettings.getJdbcUrl());
        hikariConfig.setUsername(dataSourceSettings.getUsername());
        hikariConfig.setPassword(dataSourceSettings.getPassword());
        hikariConfig.setPoolName(dataSourceSettings.getHikari().getPoolName());
        hikariConfig.setMinimumIdle(dataSourceSettings.getHikari().getMinimumIdle());
        hikariConfig.setMaximumPoolSize(dataSourceSettings.getHikari().getMaximumPoolSize());
        hikariConfig.setConnectionTestQuery(dataSourceSettings.getHikari().getConnectionTestQuery());
        hikariConfig.setMaxLifetime(dataSourceSettings.getHikari().getMaxLifetime());
        hikariConfig.setConnectionTimeout(dataSourceSettings.getHikari().getConnectionTimeout());
        hikariConfig.setAutoCommit(dataSourceSettings.getHikari().isAutoCommit());
        hikariConfig.setDataSourceProperties(dataSourceSettings.getHikari().getDataSourceProperties());
        return new HikariDataSource(hikariConfig);
    }

    @Bean("masterDataSource")
    public DataSource masterDataSource(@Qualifier("masterDataSourceSettings") DataSourceSettings dataSourceSettings){
        return createHikariDataSource(dataSourceSettings);
    }

    @Bean("slaveDataSource")
    public DataSource slaveDataSource(@Qualifier("slaveDataSourceSettings") DataSourceSettings dataSourceSettings){
        return createHikariDataSource(dataSourceSettings);
    }

    @Bean("masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean("slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "masterDataSourceTransactionManager")
    public PlatformTransactionManager masterDataSourceTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "slaveDataSourceTransactionManager")
    public PlatformTransactionManager slaveDataSourceTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public static void printDataSource(DataSource dataSource, String logPrefix){
        log.info("datasource: {}", dataSource.getClass().getName());

        HikariDataSource hikariDataSource = (HikariDataSource)dataSource;

        if(hikariDataSource.getDataSource() != null){
            log.info("{} - datasource: {}", logPrefix, hikariDataSource.getDataSource().toString());
        }else{
            log.info("{} - datasource: {}", logPrefix, "hikariDataSource.getDataSource() is null");
        }

        log.info("{} - url: {}", logPrefix, hikariDataSource.getJdbcUrl());
        log.info("{} - username: {}", logPrefix, hikariDataSource.getUsername());
        log.info("{} - password: {}", logPrefix, hikariDataSource.getPassword());
        log.info("{} - driverClassName: {}", logPrefix, hikariDataSource.getDataSourceClassName());
        log.info("{} - minimumIdle: {}", logPrefix, hikariDataSource.getMinimumIdle());
        log.info("{} - maximumPoolSize: {}", logPrefix, hikariDataSource.getMaximumPoolSize());
        log.info("{} - connectionTestQuery: {}", logPrefix, hikariDataSource.getConnectionTestQuery());
        log.info("{} - maxLifetime: {}", logPrefix, hikariDataSource.getMaxLifetime());
        log.info("{} - connectionTimeout: {}", logPrefix, hikariDataSource.getConnectionTimeout());
        log.info("{} - poolName: {}", logPrefix, hikariDataSource.getPoolName());
        log.info("{} - autoCommit: {}", logPrefix, hikariDataSource.isAutoCommit());
        for(String propName : hikariDataSource.getDataSourceProperties().stringPropertyNames()){
            String propVal = hikariDataSource.getDataSourceProperties().getProperty(propName);
            log.info("{} - dataSourceProperties: {} = {}", logPrefix, propName, propVal);
        }
        log.info("{} - running: {}", logPrefix, hikariDataSource.isRunning());
    }

}
