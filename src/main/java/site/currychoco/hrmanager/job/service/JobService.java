package site.currychoco.hrmanager.job.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.currychoco.hrmanager.job.domain.Job;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobService {

    private final JobRepository jobRepository;

    // create
    public void createJob(JobDto jobDto){
        Job job = new Job(jobDto);
        jobRepository.save(job);
    }

    // read
    public List<JobDto> selectAllJob(){
        List<Job> jobList = jobRepository.findAll();
        List<JobDto> jobDtoList = new ArrayList<>();
        for(Job j : jobList){
            jobDtoList.add(JobDto.fromEntity(j));
        }
        return jobDtoList;
    }

    /**
     * 직책 정보 수정
     */
    @Transactional
    public void modifyJob(JobDto dto){
       Job job = jobRepository.findById(dto.getJobCode()).orElseThrow();
       job.modify(dto);
    }

    /**
     * 직책 정보
     */
    public JobDto getJobByJobCode(String jobCode){
        Job job = jobRepository.findById(jobCode).orElseThrow();
        JobDto dto = JobDto.fromEntity(job);
        return dto;
    }

    /**
     * 직책 검색
     */
    public List<JobDto> getSearchedJob(String data){
        List<JobDto> dtoList = new ArrayList<>();

        List<Job> list = jobRepository.findJobByJobCodeOrJobName(data);

        for(Job job : list){
            dtoList.add(JobDto.fromEntity(job));
        }

        return dtoList;
    }

}
