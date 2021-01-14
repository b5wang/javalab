package com.b5wang.javalab.springbootex.dao.imple;


import com.b5wang.javalab.springbootex.dao.PositionDao;
import com.b5wang.javalab.springbootex.dao.PositionRepository;
import com.b5wang.javalab.springbootex.entity.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Qualifier(MySQLPositionJpaRepositoryDaoImpl.QUALIFIER)
@Transactional(readOnly = true)
@Slf4j
public class MySQLPositionJpaRepositoryDaoImpl implements PositionDao {

    public static final String QUALIFIER = "MySQLPositionJpaRepositoryDaoImpl";

    @Autowired
    private PositionRepository positionRepository;

    @Transactional
    public void saveAll(List<Position> positions) {
        positionRepository.saveAll(positions);
    }

}
