package site.currychoco.hrmanager.emp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import site.currychoco.hrmanager.emp.domain.Employee;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.repository.EmployeeAllInfoRepository;
import site.currychoco.hrmanager.emp.repository.EmployeeRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeAllInfoRepository employeeAllInfoRepository;

    // 사원 추가
    public void createEmp(EmployeeDto empDto) {
        Employee emp = new Employee(empDto);
        employeeRepository.save(emp);
    }

    // 로그인된 사원 개인정보 불러오기
    public EmployeeAllInfo getEmpInfoByEmpNo(Long empNo){
        EmployeeAllInfo empAllInfo = employeeAllInfoRepository.getAllByEmpNo(empNo);
        return empAllInfo;
    }

    // 이름으로 사원 정보 리스트 가져오기
    public List<EmployeeAllInfo> getListByName(String name){
        List<EmployeeAllInfo> list = new ArrayList<>();

        list = employeeAllInfoRepository.findAllByEmpName(name);
        for(EmployeeAllInfo emp : list){
            if(emp.getId() == null){
                emp.setId("-");
            }
        }

        return list;
    }

    // 사번으로 사원 정보 리스트 가져오기
    public List<EmployeeAllInfo> getListByEmpNo(Long empNo){
        List<EmployeeAllInfo> list = new ArrayList<>();

        list = employeeAllInfoRepository.findAllByEmpNo(empNo);

        return list;
    }

}
