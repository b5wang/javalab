package com.b5wang.javalab.springbootex.dao;

import com.b5wang.javalab.springbootex.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, String>, JpaSpecificationExecutor<Position> {

    @Override
    Optional<Position> findById(String id);

    @Query(value = "SELECT * FROM t_position LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<Position> find(int offset, int limit);

}
