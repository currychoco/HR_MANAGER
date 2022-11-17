package site.currychoco.hrmanager.emp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import site.currychoco.hrmanager.emp.domain.Employee;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfoDto;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.repository.EmployeeRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 로그인된 사원 개인정보 불러오기
    public EmployeeAllInfoDto getEmpInfoByEmpNo(Long empNo){
        EmployeeAllInfoDto empAllInfo = employeeRepository.getAllByEmpNo(empNo);
        return empAllInfo;
    }

}
