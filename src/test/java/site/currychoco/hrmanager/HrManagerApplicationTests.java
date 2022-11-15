package site.currychoco.hrmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.currychoco.hrmanager.department.domain.Department;
import site.currychoco.hrmanager.department.repository.DepartmentRepository;
import site.currychoco.hrmanager.emp.repository.EmployeeRepository;
import site.currychoco.hrmanager.job.controller.JobController;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.repository.JobRepository;
import site.currychoco.hrmanager.job.service.JobService;
import site.currychoco.hrmanager.position.repository.PositionRepository;

import java.util.List;

@SpringBootTest
class HrManagerApplicationTests {
}
