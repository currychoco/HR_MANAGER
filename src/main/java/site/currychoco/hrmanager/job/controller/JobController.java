package site.currychoco.hrmanager.job.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.job.domain.Job;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.service.JobService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class JobController {

    private final JobService jobService;

    // ---
    // page
    // ---
    @GetMapping("/manager/job/create")
    public String createJob(){return "manager/job/addJob";}

    // ---
    // api
    // ---

    // create
    @ResponseBody
    @PostMapping("/job/add")
    public void addJob(@RequestBody JobDto jobDto){
        jobService.createJob(jobDto);
    }

    // read
    @ResponseBody
    @GetMapping("/job/all/read")
    public List<JobDto> getAllJob(){
        List<JobDto> list = jobService.selectAllJob();
        return list;
    }

    // update
    @ResponseBody
    @PostMapping("/job/update")
    public boolean updateJob(@RequestBody JobDto jobDto){
        JobDto result = jobService.updateJob(jobDto);
        return result != null;
    }

    // delete
    @ResponseBody
    @PostMapping("/job/delete")
    public void deleteJobById(String jobCode){
        jobService.deleteJobById(jobCode);
    }
}
