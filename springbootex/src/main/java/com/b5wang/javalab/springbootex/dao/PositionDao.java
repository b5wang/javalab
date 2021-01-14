package com.b5wang.javalab.springbootex.dao;

import com.b5wang.javalab.springbootex.entity.Position;

import java.util.List;

public interface PositionDao {

    void saveAll(List<Position> positions);

}
