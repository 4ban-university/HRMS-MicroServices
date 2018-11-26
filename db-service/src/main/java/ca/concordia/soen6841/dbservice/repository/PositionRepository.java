package ca.concordia.soen6841.dbservice.repository;

import ca.concordia.soen6841.dbservice.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
