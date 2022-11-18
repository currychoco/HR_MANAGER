package site.currychoco.hrmanager.emp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 로그인한 회원의 사번을 통해 회원 정보 가져오기
    @ResponseBody
    @GetMapping("/get/employee/all/info")
    public EmployeeAllInfo getAllInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long empNo = (Long)session.getAttribute("empNo");

        System.out.println("employeeService: " + employeeService);
        EmployeeAllInfo allInfoDto = employeeService.getEmpInfoByEmpNo(empNo);
        return allInfoDto;
    }


    // 사번이나 이름 받기 -> 사번인지 이름인지 확인하기
    @GetMapping("/get/name/empno")
    public List<EmployeeAllInfo> getAllInfo(@RequestParam String data){

        List<EmployeeAllInfo> empList = new ArrayList<>();

        try {
            Long empNo = Long.parseLong(data);
            empList = employeeService.getListByEmpNo(empNo);
        }catch(Exception e){
            empList = employeeService.getListByName(data);
        }

        return empList;

    }

}
