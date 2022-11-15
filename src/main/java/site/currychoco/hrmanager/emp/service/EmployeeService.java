package site.currychoco.hrmanager.emp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.repository.EmployeeRepository;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

}
