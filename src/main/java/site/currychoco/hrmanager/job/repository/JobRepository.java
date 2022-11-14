package site.currychoco.hrmanager.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.job.domain.Job;

public interface JobRepository extends JpaRepository<Job, String> {

    @Modifying
    @Query(value="update job set job_name = :jobName, job_name_en = :jobNameEn, job_level = :jobLevel where job_code = :jobCode", nativeQuery = true)
    Job updateJob(String jobCode, String jobName, String jobNameEn, int jobLevel);

}
