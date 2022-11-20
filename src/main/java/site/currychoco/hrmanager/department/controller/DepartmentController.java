package site.currychoco.hrmanager.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.department.domain.DepartmentDto;
import site.currychoco.hrmanager.department.service.DepartmentService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    // ---
    // page
    // ---
    @GetMapping("/manager/department/create")
    public String createDepartment(){return "manager/department/addDepartment";}


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
}
