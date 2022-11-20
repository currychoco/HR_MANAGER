package site.currychoco.hrmanager.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.job.domain.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {

    @Query(value = "select * from job where job_code like CONCAT('%',:data,'%') or job_name like CONCAT('%',:data,'%')", nativeQuery = true)
    List<Job> findJobByJobCodeOrJobName(String data);

}
