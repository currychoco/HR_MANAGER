package site.currychoco.hrmanager.job.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import site.currychoco.hrmanager.department.domain.DepartmentDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job")
@NoArgsConstructor
@Getter
@ToString
public class Job {
    @Id
    @Column(name = "job_code")
    private String jobCode;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_name_en")
    private String jobNameEn;
    @Column(name = "job_level")
    private int jobLevel;

    public Job(JobDto jobDto){
        this.jobCode = jobDto.getJobCode();
        this.jobName = jobDto.getJobName();
        this.jobNameEn = jobDto.getJobNameEn();
        this.jobLevel = jobDto.getJobLevel();
    }

    public void modify(JobDto dto){
        this.jobName = dto.getJobName();
        this.jobNameEn = dto.getJobNameEn();
        this.jobLevel = dto.getJobLevel();
    }
}
