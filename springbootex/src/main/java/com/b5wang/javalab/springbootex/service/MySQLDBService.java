package com.b5wang.javalab.springbootex.service;

import com.b5wang.javalab.springbootex.config.DataSourceConfig;
import com.b5wang.javalab.springbootex.config.DataSourceSettings;
import com.b5wang.javalab.springbootex.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Slf4j
@Service
public class MySQLDBService {

    private final static String INSERT_INTO_USER = "insert into user(id,username,password,email,create_time) values(?,?,?,?,?)";

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    @Autowired
    @Qualifier("masterJdbcTemplate")
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    @Qualifier("slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;

    @Autowired
    @Qualifier("masterDataSourceSettings")
    private DataSourceSettings masterDataSourceSettings;

    @Autowired
    @Qualifier("slaveDataSourceSettings")
    private DataSourceSettings slaveDataSourceSettings;

    @PostConstruct
    public void init(){
    }

    @Transactional(value="masterDataSourceTransactionManager")
    public void createUserInMasterDB(User user){
        int masterResult = masterJdbcTemplate.update(
                INSERT_INTO_USER,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, user.getId());
                        ps.setString(2,user.getUserName());
                        ps.setString(3,user.getPassword());
                        ps.setString(4,user.getEmail());
                        ps.setLong(5,user.getUpdateTime());
                    }
                });
        log.info("addUser - masterResult: {}",masterResult);
        DataSourceConfig.printDataSource(masterDataSource,"masterDataSource");
    }

    @Transactional(value="slaveDataSourceTransactionManager")
    public void createUserInSlaveDB(User user){
        int slaveResult = slaveJdbcTemplate.update(INSERT_INTO_USER,user.getId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getUpdateTime());
        log.info("addUser - slaveResult: {}",slaveResult);
        DataSourceConfig.printDataSource(slaveDataSource,"slaveDataSource");
    }

}
