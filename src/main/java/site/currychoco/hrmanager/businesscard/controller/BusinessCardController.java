package site.currychoco.hrmanager.businesscard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.businesscard.domain.BusiCardDto;
import site.currychoco.hrmanager.businesscard.service.BusinessCardService;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.service.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    private final EmployeeService employeeService;

    @GetMapping("/business-card")
    public String application(){
        return "businesscard/applicationCard";
    }

    @GetMapping("/manager/busi-card/allow")
    public String allowBusiCard(){return "manager/allow/busiAllowList";}

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

    /**
     * 명함 신청
     */
    @ResponseBody
    @PostMapping("/busi-card/request")
    public void requestBusiCard(HttpSession session){
        Long empNo = (Long)session.getAttribute("empNo");
        EmployeeAllInfo info = employeeService.getEmpInfoByEmpNo(empNo);
        BusiCardDto busiCardDto = BusiCardDto.fromEntity(info);
        businessCardService.requestBusiCard(busiCardDto);
    }

    /**
     * 명함 신청 리스트
     */
    @ResponseBody
    @GetMapping("/busi-card/list")
    public List<BusiCardDto> listBusiCard(){
        List<BusiCardDto> list = businessCardService.getBusiList();
        return list;
    }
}
