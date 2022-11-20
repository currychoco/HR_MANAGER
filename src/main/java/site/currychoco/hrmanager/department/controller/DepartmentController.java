package site.currychoco.hrmanager.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.department.domain.DepartmentDto;
import site.currychoco.hrmanager.department.service.DepartmentService;
import site.currychoco.hrmanager.emp.service.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    private final EmployeeService employeeService;

    // ---
    // page
    // ---
    @GetMapping("/manager/department/create")
    public String createDepartment(){return "manager/department/addDepartment";}

    @GetMapping("/manager/department/search")
    public String searchDepartment(){return "manager/department/searchDepartment";}

    @GetMapping("/manager/department/detail")
    public String detailDepartment(@RequestParam(name = "deptCode") String deptCode, Model model){

        DepartmentDto deptDto = departmentService.getDepartmentByDeptCode(deptCode);

        model.addAttribute("department", deptDto);

        return "manager/department/detailDepartment";
    }

    // ---
    // api
    // ---

    /**
     * 새로운 부서 생성
     */
    @ResponseBody
    @PostMapping("/department/add")
    public void addDepartment(@RequestBody DepartmentDto dto){
        departmentService.addDepartment(dto);
    }

    /**
     *
     * 모든 부서 출력
     */
    @ResponseBody
    @GetMapping("/department/all")
    public List<DepartmentDto> getAllDept(){
        List<DepartmentDto> list = departmentService.getAllDept();
        return list;
    }

    /**
     * 부서명 or 부서코드에 포함되는 정보 출력
     */
    @ResponseBody
    @GetMapping("/department/search")
    public List<DepartmentDto> getAllInfo(@RequestParam String data){

        List<DepartmentDto> deptList = departmentService.getSearchedDepartments(data);

        return deptList;
    }

    /**
     *  부서 정보 수정
     */
    @ResponseBody
    @PostMapping("/department/modify")
    public void modifyDepartment(@RequestBody DepartmentDto dto){
        departmentService.modifyDepartment(dto);
    }

}
