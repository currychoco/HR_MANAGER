package site.currychoco.hrmanager.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "log/login";
    }

    @GetMapping("/join")
    public String join(){
        return "log/join";
    }

    @GetMapping("/empinfo")
    public String userInfo(){return "employee/empInfo";}

}