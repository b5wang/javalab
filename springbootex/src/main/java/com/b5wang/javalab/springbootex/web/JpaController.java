package com.b5wang.javalab.springbootex.web;


import com.b5wang.javalab.springbootex.dao.PositionDao;
import com.b5wang.javalab.springbootex.dao.PositionRepository;
import com.b5wang.javalab.springbootex.dao.imple.MySQLPositionJpaRepositoryDaoImpl;
import com.b5wang.javalab.springbootex.entity.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
public class JpaController {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    @Qualifier(MySQLPositionJpaRepositoryDaoImpl.QUALIFIER)
    private PositionDao repositoryDao;

    @RequestMapping(value="/jpa/batchUpdate/{offset}/{limit}",method = RequestMethod.GET)
    @ResponseBody
    public String batchUpdate(@PathVariable("offset") int offset, @PathVariable("limit") int limit){

        log.info("---->");

        List<Position> positions = positionRepository.find(offset,limit);
        for(Position position:positions){
            position.setStatus("5");
        }

        long currentTime = System.currentTimeMillis();
        Position newPosition = new Position();
        newPosition.setAccountNo("86000001");
        newPosition.setProduct("AUD/CHF");
        newPosition.setLeverage(1L);
        newPosition.setDirection("BUY");
        newPosition.setPrice(BigDecimal.valueOf(0.8888));
        newPosition.setOpenTime(currentTime);
        newPosition.setMargin(BigDecimal.ZERO);
        newPosition.setTakeProfit(BigDecimal.valueOf(0.123));
        newPosition.setStopLoss(BigDecimal.valueOf(0.123));
        newPosition.setTickTime(currentTime);
        newPosition.setUpdateTime(currentTime);
        newPosition.setInitVolume(BigDecimal.ZERO);
        newPosition.setCommission(BigDecimal.ZERO);
        newPosition.setContractSize(0L);
        newPosition.setDigits(0L);
        newPosition.setProductAskSpread(BigDecimal.valueOf(0.66830));
        newPosition.setProductBidSpread(BigDecimal.valueOf(0.66710));
        newPosition.setCloseTime(currentTime);
        newPosition.setCloseVolume(BigDecimal.ZERO);
        newPosition.setProfit(BigDecimal.ZERO);
        newPosition.setPositionId(6434454407156L);
        newPosition.setStatus("5");
        //positions.add(newPosition);

        long startSave = System.currentTimeMillis();

        repositoryDao.saveAll(positions);// repository

        long endSave = System.currentTimeMillis();
        long time = endSave - startSave;
        log.info("Take time: {}",time);
        return "Done! It takes " + time;
    }

}
