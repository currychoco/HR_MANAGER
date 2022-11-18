package site.currychoco.hrmanager.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {
    @GetMapping("/manager")
    public String manager(){return "manager/managerPage";}

    @GetMapping("/add/employee")
    public String addEmployee(){return "manager/addEmployee";}
}
