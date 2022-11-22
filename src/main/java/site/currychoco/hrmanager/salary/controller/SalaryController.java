package site.currychoco.hrmanager.salary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;
import site.currychoco.hrmanager.salary.domain.SalaryDto;
import site.currychoco.hrmanager.salary.service.SalaryService;

@RequiredArgsConstructor
@Controller
public class SalaryController {

    private final SalaryService salaryService;

    private final EmployeeService employeeService;

    // ---
    // page
    // ---
    @CheckAuthority(authCode = "g000013")
    @GetMapping("/manager/salary/detail")
    public String detailSalary(@RequestParam(name = "empNo") Long empNo, Model model){

        SalaryDto salaryDto = salaryService.getSalaryByEmpNo(empNo);
        EmployeeDto employeeDto = employeeService.getEmployeeByEmpNo(empNo);

        model.addAttribute("salary", salaryDto);
        model.addAttribute("employee", employeeDto);

        return "manager/salary/detailSalary";
    }

    // ---
    // api
    // ---

    /**
     * 연봉 액수 수정
     */
    @CheckAuthority(authCode = "g000014")
    @ResponseBody
    @PostMapping("/salary/modify")
    public void modifySalary(@RequestBody SalaryDto dto){
        salaryService.modifySalary(dto);
    }
}
