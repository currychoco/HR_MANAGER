package site.currychoco.hrmanager.myInformation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;
import site.currychoco.hrmanager.myInformation.domain.MyInformationDto;
import site.currychoco.hrmanager.myInformation.service.MyInformationService;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MyInformationController {

    private final MyInformationService myInformationService;

    private final EmployeeService employeeService;

    // ---
    // page
    // ---
    @GetMapping("/manager/allow/list")
    public String allowList(){return "manager/allow/allowList";}

    @GetMapping("/manager/allow/detail")
    public String detailAllow(@RequestParam Long no, @RequestParam Long empNo, Model model){
        EmployeeDto empDto = employeeService.getEmployeeByEmpNo(empNo);
        MyInformationDto myDto = myInformationService.getMyInformation(no);

        model.addAttribute("employee", empDto);
        model.addAttribute("myInformation", myDto);
        return "manager/allow/detailAllow";
    }

    // ---
    // api
    // ---
    @ResponseBody
    @PostMapping("/my-info/create")
    public void createOwnInfo(@RequestBody MyInformationDto dto){
        dto.setRequestDate(new Timestamp(System.currentTimeMillis()));
        dto.setAllow("r");
        myInformationService.saveOwnInfo(dto);
    }

    @ResponseBody
    @GetMapping("/allow/list")
    public List<MyInformationDto> getAllowList(){
        List<MyInformationDto> list = myInformationService.getAllowList();
        return list;
    }

    @ResponseBody
    @PostMapping("/allow/update")
    public void updateAllow(@RequestBody MyInformationDto dto){
        MyInformationDto myInfoDto = myInformationService.getMyInformation(dto.getNo());
        dto.setAllow("y");
        dto.setRequestDate(myInfoDto.getRequestDate());
        dto.setAllowDate(new Timestamp(System.currentTimeMillis()));
        myInformationService.allowModify(dto);

        EmployeeDto empDto = employeeService.getEmployeeByEmpNo(dto.getEmpNo());
        empDto.setEmpName(dto.getEmpName());
        empDto.setEmpNameEn(dto.getEmpNameEn());
        empDto.setGender(dto.getGender());
        empDto.setEmail(dto.getEmail());
        empDto.setPhone(dto.getPhone());
        employeeService.updateEmployee(empDto);
    }
}
