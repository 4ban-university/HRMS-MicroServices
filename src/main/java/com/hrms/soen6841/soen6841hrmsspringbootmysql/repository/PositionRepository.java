package com.hrms.soen6841.soen6841hrmsspringbootmysql.repository;

import com.hrms.soen6841.soen6841hrmsspringbootmysql.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
