package site.currychoco.hrmanager.emp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import site.currychoco.hrmanager.emp.service.EmployeeService;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
}
