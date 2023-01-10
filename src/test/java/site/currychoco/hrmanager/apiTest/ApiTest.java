package site.currychoco.hrmanager.apiTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;

import java.util.List;

@SpringBootTest
public class ApiTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    void 출력되나테스트(){
        List<EmployeeDto> list = employeeService.getAllEmployee();
        System.out.println(list);
    }

}
