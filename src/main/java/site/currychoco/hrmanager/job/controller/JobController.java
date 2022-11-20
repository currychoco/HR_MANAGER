package site.currychoco.hrmanager.job.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/manager/job/search")
    public String searchJob(){return "manager/job/searchJob";}

    @GetMapping("/manager/job/detail")
    public String detailJob(@RequestParam(name = "jobCode") String jobCode, Model model){
        JobDto jobDto = jobService.getJobByJobCode(jobCode);

        model.addAttribute("job", jobDto);

        return "manager/job/detailJob";
    }

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

    /**
     * 직책 정보 수정
     */
    @ResponseBody
    @PostMapping("/job/modify")
    public void modifyJob(@RequestBody JobDto jobDto){
        jobService.modifyJob(jobDto);
    }


    /**
     *  직책 검색
     */
    @ResponseBody
    @GetMapping("/job/search")
    public List<JobDto> searchJob(String data){
        return jobService.getSearchedJob(data);
    }
}
