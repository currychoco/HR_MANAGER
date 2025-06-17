package site.currychoco.hrmanager.emp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.dto.EmployeeAllInfoDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @CheckAuthority(authCode = "g000002")
    @GetMapping("/manager/employee2/{empNo}")
    public ResponseEntity<EmployeeAllInfoDto> employeeDetail(@PathVariable(name = "empNo") Long empNo){
        return ResponseEntity.ok(EmployeeAllInfoDto.fromEntity(employeeService.getEmpInfoByEmpNo(empNo)));
    }

    // 로그인한 회원의 사번을 통해 회원 정보 가져오기
    @GetMapping("/get/employee/all/info")
    public ResponseEntity<EmployeeAllInfoDto> getAllInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long empNo = (Long)session.getAttribute("empNo");

        return ResponseEntity.ok(EmployeeAllInfoDto.fromEntity(employeeService.getEmpInfoByEmpNo(empNo)));
    }

    // 사번이나 이름 받기 -> 사번인지 이름인지 확인하기
    @CheckAuthority(authCode = "g000002")
    @GetMapping("/get/name/empno")
    public ResponseEntity<List<EmployeeAllInfoDto>> getAllInfo(@RequestParam String data){

        List<EmployeeAllInfo> empList = new ArrayList<>();

        try {
            Long empNo = Long.parseLong(data);
            empList = employeeService.getListByEmpNo(empNo);
        } catch(NumberFormatException nfe){
            empList = employeeService.getListByName(data);
        }

        return ResponseEntity.ok(empList.stream().map(EmployeeAllInfoDto::fromEntity).collect(Collectors.toList()));
    }

    // 새로운 사번 추가
    @CheckAuthority(authCode = "g000000")
    @PostMapping("/employee/add")
    public ResponseEntity<Void> addNemEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto dto = employeeService.addNewEmployee(employeeDto);

        return ResponseEntity.ok(null);
    }

    @CheckAuthority(authCode = "g000001")
    @PostMapping("/employee/update")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.updateEmployee(employeeDto);
    }

}
