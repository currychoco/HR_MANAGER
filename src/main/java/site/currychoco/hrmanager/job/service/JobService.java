package site.currychoco.hrmanager.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.currychoco.hrmanager.job.domain.Job;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    // create
    public JobDto insertJob(JobDto jobDto){
        Job job = new Job(jobDto);
        Job insertedJob = jobRepository.save(job);
        JobDto result = JobDto.fromEntity(insertedJob);
        return result;
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

    // update
    @Transactional
    public JobDto updateJob(JobDto jobDto){
        Job job = new Job(jobDto);
        Job updatedJob = jobRepository.updateJob(job.getJobCode(), job.getJobName(), job.getJobNameEn(), job.getJobLevel());
        JobDto result = JobDto.fromEntity(updatedJob);
        return result;
    }

    //read
    public void deleteJobById(String jobCode){
        jobRepository.deleteById(jobCode);
    }
}
