package site.currychoco.hrmanager.businesscard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.businesscard.domain.BusiCard;

public interface BusiCardRepository extends JpaRepository<BusiCard, Long> {
}
