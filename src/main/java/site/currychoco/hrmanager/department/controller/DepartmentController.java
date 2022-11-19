package site.currychoco.hrmanager.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.department.domain.DepartmentDto;
import site.currychoco.hrmanager.department.service.DepartmentService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 수정 필요
    @ResponseBody
    @GetMapping("/department/all")
    public List<DepartmentDto> getAllDept(){
        List<DepartmentDto> list = departmentService.getAllDept();
        return list;
    }
}
