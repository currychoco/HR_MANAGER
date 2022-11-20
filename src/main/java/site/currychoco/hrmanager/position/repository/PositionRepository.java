package site.currychoco.hrmanager.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.position.domain.Position;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, String> {

    @Query(value = "select * from position where position_code like CONCAT('%',:data,'%') or position_name like CONCAT('%',:data,'%')", nativeQuery = true)
    List<Position> findPositionByPositionCodeOrPositionName(String data);
}
