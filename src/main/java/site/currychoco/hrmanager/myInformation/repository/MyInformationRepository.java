package site.currychoco.hrmanager.myInformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.myInformation.domain.MyInformation;

public interface MyInformationRepository extends JpaRepository<MyInformation, Long> {
}
