package site.currychoco.hrmanager.businesscard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.businesscard.service.BusinessCardService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    @GetMapping("/business-card")
    public String application(){
        return "businesscard/applicationCard";
    }

    /**
     * 로그인 된 계정의 사원정보로 이루어진 명함 미리보기 이미지 다운로드
     */
    @ResponseBody
    @GetMapping("/business-card/image")
    public void showPreviewBusinessCard(HttpSession session
            , HttpServletResponse res
            , @RequestParam(name = "cardType") String cardType) {

        long empNo = Long.parseLong(session.getAttribute("empNo").toString());

        businessCardService.responseBusinessCardImage(empNo, cardType, res);
    }
}
