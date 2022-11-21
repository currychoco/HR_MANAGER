package site.currychoco.hrmanager.emp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.department.domain.DepartmentDto;
import site.currychoco.hrmanager.department.service.DepartmentService;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;
import site.currychoco.hrmanager.job.domain.JobDto;
import site.currychoco.hrmanager.job.service.JobService;
import site.currychoco.hrmanager.position.domain.PositionDto;
import site.currychoco.hrmanager.position.service.PositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    private final JobService jobService;

    private final PositionService positionService;

    // ---
    // page
    // ---

    @GetMapping("/manager/employee/add")
    public String addEmployee(Model model){
        List<DepartmentDto> departments = departmentService.getAllDept();
        List<JobDto> jobs = jobService.selectAllJob();
        List<PositionDto> positions = positionService.getAllPosition();

        model.addAttribute("departments", departments);
        model.addAttribute("jobs", jobs);
        model.addAttribute("positions", positions);

        return "manager/employee/addEmployee";
    }

    @GetMapping("/manager/employee/search")
    public String employeeSearch(){return "manager/employee/employeeSearch";}

    @GetMapping("/manager/employee/detail")
    public String employeeDetail(@RequestParam(name = "empNo") Long empNo, Model model){
        EmployeeAllInfo employee = employeeService.getEmpInfoByEmpNo(empNo);
        List<DepartmentDto> departments = departmentService.getAllDept();
        List<JobDto> jobs = jobService.selectAllJob();
        List<PositionDto> positions = positionService.getAllPosition();

        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("jobs", jobs);
        model.addAttribute("positions", positions);

        return "manager/employee/detailEmployeeInfo";
    }

    // ---
    // api
    ///

    // 로그인한 회원의 사번을 통해 회원 정보 가져오기
    @ResponseBody
    @GetMapping("/get/employee/all/info")
    public EmployeeAllInfo getAllInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long empNo = (Long)session.getAttribute("empNo");

        EmployeeAllInfo allInfoDto = employeeService.getEmpInfoByEmpNo(empNo);
        return allInfoDto;
    }

    // 사번이나 이름 받기 -> 사번인지 이름인지 확인하기
    @ResponseBody
    @GetMapping("/get/name/empno")
    public List<EmployeeAllInfo> getAllInfo(@RequestParam String data){

        List<EmployeeAllInfo> empList = new ArrayList<>();

        try {
            Long empNo = Long.parseLong(data);
            empList = employeeService.getListByEmpNo(empNo);
        } catch(NumberFormatException nfe){
            empList = employeeService.getListByName(data);
        }

        return empList;
    }

    // 새로운 사번 추가
    @ResponseBody
    @PostMapping("/employee/add")
    public boolean addNemEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto dto = employeeService.addNewEmployee(employeeDto);

        return dto != null;
    }

    @ResponseBody
    @PostMapping("/employee/update")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto) {

        employeeService.updateEmployee(employeeDto);

    }

}
