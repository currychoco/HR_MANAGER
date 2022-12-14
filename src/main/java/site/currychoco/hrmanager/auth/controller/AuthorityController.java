package site.currychoco.hrmanager.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.currychoco.hrmanager.auth.domain.AuthorityDto;
import site.currychoco.hrmanager.auth.service.AuthorityService;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthorityController {

    private final AuthorityService authorityService;

    @CheckAuthority(authCode = "g000009")
    @GetMapping("/manager/authority/grant")
    public String grantAuthority(Model model){
        List<AuthorityDto> authList = authorityService.getAllAuthority();

        model.addAttribute("authList", authList);
        return "manager/authority/grantAuthority";
    }
}
