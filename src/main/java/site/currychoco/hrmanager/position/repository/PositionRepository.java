package site.currychoco.hrmanager.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.position.domain.Position;

public interface PositionRepository extends JpaRepository<Position, String> {
}
