package site.currychoco.hrmanager.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.currychoco.hrmanager.department.domain.DepartmentDto;
import site.currychoco.hrmanager.department.service.DepartmentService;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.service.JobService;
import site.currychoco.hrmanager.position.domain.PositionDto;
import site.currychoco.hrmanager.position.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/external-api")
@RequiredArgsConstructor
public class ExternalApiController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobService jobService;
    private final PositionService positionService;

    @GetMapping("/employee")
    public List<EmployeeDto> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/department")
    public List<DepartmentDto> getAllDepartment(){
        return departmentService.getAllDept();
    }

    @GetMapping("/job")
    public List<JobDto> getAllJob(){
        return jobService.selectAllJob();
    }

    @GetMapping("/position")
    public List<PositionDto> getAllPosition(){
        return positionService.getAllPosition();
    }
}
