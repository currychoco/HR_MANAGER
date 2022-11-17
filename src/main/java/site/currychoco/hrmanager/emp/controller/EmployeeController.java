package site.currychoco.hrmanager.emp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfoDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 로그인한 회원의 모든 정보 가져오기
    @GetMapping("/get/employee/all/info")
    public EmployeeAllInfoDto getAllInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long empNo = (Long)session.getAttribute("empNo");
        EmployeeAllInfoDto allInfoDto = employeeService.getEmpInfoByEmpNo(empNo);
        return allInfoDto;
    }
}
