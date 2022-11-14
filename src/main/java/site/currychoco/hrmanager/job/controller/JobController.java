package site.currychoco.hrmanager.job.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.currychoco.hrmanager.job.domain.Job;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.service.JobService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    // create
    @PostMapping("/job/add")
    public boolean addJob(@RequestBody JobDto jobDto){
        JobDto addedJob = jobService.insertJob(jobDto);
        return addedJob != null;
    }

    // read
    @GetMapping("/job/all/read")
    public List<JobDto> getAllJob(){
        List<JobDto> list = jobService.selectAllJob();
        return list;
    }

    // update
    @PostMapping("/job/update")
    public boolean updateJob(@RequestBody JobDto jobDto){
        JobDto result = jobService.updateJob(jobDto);
        return result != null;
    }

    // delete
    @PostMapping("/job/delete")
    public void deleteJobById(String jobCode){
        jobService.deleteJobById(jobCode);
    }
}
