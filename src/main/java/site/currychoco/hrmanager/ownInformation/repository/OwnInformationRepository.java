package site.currychoco.hrmanager.ownInformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.ownInformation.domain.OwnInformation;

public interface OwnInformationRepository extends JpaRepository<OwnInformation, Long> {
}
