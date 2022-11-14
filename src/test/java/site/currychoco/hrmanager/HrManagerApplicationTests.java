package site.currychoco.hrmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.currychoco.hrmanager.dept.domain.Department;
import site.currychoco.hrmanager.dept.repository.DepartmentRepository;
import site.currychoco.hrmanager.emp.domain.Employee;
import site.currychoco.hrmanager.emp.repository.EmployeeRepository;
import site.currychoco.hrmanager.job.controller.JobController;
import site.currychoco.hrmanager.job.domain.Job;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.repository.JobRepository;
import site.currychoco.hrmanager.job.service.JobService;
import site.currychoco.hrmanager.position.domain.Position;
import site.currychoco.hrmanager.position.repository.PositionRepository;

import java.util.List;

@SpringBootTest
class HrManagerApplicationTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobController jobController;

    @Autowired
    private JobService jobService;

    @Test
    void 직책_전체_읽기_테스트(){
        List<JobDto> list = jobController.getAllJob();
        System.out.println(list);
    }

    @Test
    void 직책_생성_테스트(){
        String jobCode = "a02";
        String jobName = "실장";
        String jobNameEn="roomboss";
        int jobLevel = 2;
        boolean result = jobController.addJob(new JobDto(jobCode, jobName, jobNameEn, jobLevel));
    }

    @Test
    void 부서엔티티_생성_테스트() {

        final String deptCode = "000000";
        final String upperDeptCode = null;
        final Integer headEmpNo = null;
        final String deptName = "병관이네";
        final String deptNameEn = "BGINE";
        final int deptLevel = 1;


        Department newDept = new Department(deptCode, upperDeptCode, headEmpNo, deptName, deptNameEn, deptLevel);
        departmentRepository.save(newDept);

        Department selectedDept = departmentRepository.findById(deptCode).orElse(new Department());
        System.out.println(selectedDept);
    }

    @Test
    void 직책_수정_테스트(){
        String jobCode = "test1";
        String jobName = "실장수정";
        String jobNameEn="roomboss수정";
        int jobLevel = 4;
        JobDto jobDto = new JobDto(jobCode, jobName, jobNameEn, jobLevel);
        jobService.updateJob(jobDto);
    }

    @Test
    void 직책_삭제_테스트(){
        직책_전체_읽기_테스트();
        jobService.deleteJobById("000000");
        직책_전체_읽기_테스트();
    }
}
