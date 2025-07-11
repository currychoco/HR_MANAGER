package site.currychoco.hrmanager.emp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.currychoco.hrmanager.core.exception.BadRequestException;
import site.currychoco.hrmanager.emp.domain.Employee;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.repository.EmployeeAllInfoRepository;
import site.currychoco.hrmanager.emp.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeAllInfoRepository employeeAllInfoRepository;

    // 사원 추가
    @Transactional
    public void createEmp(EmployeeDto empDto) {
        Employee emp = new Employee(empDto);
        employeeRepository.save(emp);
    }

    /**
     * 사원 개인정보 불러오기
     */
    public EmployeeAllInfo getEmpInfoByEmpNo(Long empNo){
        EmployeeAllInfo empAllInfo = employeeAllInfoRepository.findByEmpNo(empNo).orElseThrow(()->new BadRequestException("해당 사원이 존재하지 않습니다"));
        return empAllInfo;
    }

    // 이름으로 사원 정보 리스트 가져오기
    public List<EmployeeAllInfo> getListByName(String name){
        return employeeAllInfoRepository.findByEmpNameContaining(name);
    }

    // 사번으로 사원 정보 리스트 가져오기
    public List<EmployeeAllInfo> getListByEmpNo(Long empNo){
        List<EmployeeAllInfo> list = new ArrayList<>();
        EmployeeAllInfo empAllInfo = employeeAllInfoRepository.findById(empNo).orElseThrow(()->new BadRequestException("해당 사원이 존재하지 않습니다"));
        list.add(empAllInfo);

        return list;
    }

    /**
     * 사원 추가
     */
    public EmployeeDto addNewEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee(employeeDto);
        Employee insertedEmployee = employeeRepository.save(employee);
        EmployeeDto dto = EmployeeDto.fromEntity(insertedEmployee);
        return dto;
    }

    /**
     * 사원 정보 수정
     */
    @Transactional
    public void updateEmployee(EmployeeDto employeeDto) {
        Employee emp = employeeRepository.findById(employeeDto.getEmpNo()).orElseThrow();
        emp.update(employeeDto);
    }

    /**
     * 사번으로 사원 정보 출력
     */
    public EmployeeDto getEmployeeByEmpNo(Long empNo){
        Employee employee = employeeRepository.findById(empNo).orElseThrow();
        EmployeeDto dto = EmployeeDto.fromEntity(employee);
        return dto;
    }

    /**
     * 모든 사원 정보
     */
    public List<EmployeeDto> getAllEmployee(){
        List<Employee> list = employeeRepository.findAll();
        List<EmployeeDto> dtoList = new ArrayList<>();

        for(Employee e : list){
            dtoList.add(EmployeeDto.fromEntity(e));
        }

        return dtoList;
    }
}
