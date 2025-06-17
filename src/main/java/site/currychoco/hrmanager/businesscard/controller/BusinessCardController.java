package site.currychoco.hrmanager.businesscard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.businesscard.domain.BusiCardDto;
import site.currychoco.hrmanager.businesscard.service.BusinessCardService;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.service.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    private final EmployeeService employeeService;

    @CheckAuthority(authCode = "g000016")
    @GetMapping("/manager/busi-card-allow/{no}")
    public ResponseEntity<BusiCardDto> getBusicardAllowByNo(@PathVariable Long no) {
        return ResponseEntity.ok(businessCardService.getBusiCardDtoByNo(no));
    }

    /**
     * 로그인 된 계정의 사원정보로 이루어진 명함 미리보기 이미지 다운로드
     */
    @GetMapping("/business-card/image")
    public ResponseEntity<Void> showPreviewBusinessCard(HttpSession session
            , HttpServletResponse res
            , @RequestParam(name = "cardType") String cardType) {

        long empNo = Long.parseLong(session.getAttribute("empNo").toString());

        businessCardService.responseBusinessCardImage(empNo, cardType, res);

        return ResponseEntity.ok(null);
    }

    /**
     * 명함 신청
     */
    @PostMapping("/busi-card/request")
    public ResponseEntity<Void> requestBusiCard(HttpSession session){
        Long empNo = (Long)session.getAttribute("empNo");
        EmployeeAllInfo info = employeeService.getEmpInfoByEmpNo(empNo);
        BusiCardDto busiCardDto = BusiCardDto.fromEntity(info);
        businessCardService.requestBusiCard(busiCardDto);

        return ResponseEntity.ok(null);
    }

    /**
     * 명함 신청 리스트
     */
    @CheckAuthority(authCode = "g000016")
    @GetMapping("/busi-card/list")
    public ResponseEntity<List<BusiCardDto>> listBusiCard(){
        return ResponseEntity.ok(businessCardService.getBusiList());
    }

    /**
     * 명함 신청 승인
     */
    @CheckAuthority(authCode = "g000016")
    @PostMapping("/allow/busi-card")
    public ResponseEntity<Void> busiCardAllow(@RequestBody BusiCardDto busiCardDto){
        BusiCardDto dto = businessCardService.getBusiCardDtoByNo(busiCardDto.getNo());
        busiCardDto.setRequestDate(dto.getRequestDate());
        busiCardDto.setAllowDate(new Timestamp(System.currentTimeMillis()));
        busiCardDto.setAllow("Y");
        businessCardService.allowBusiCard(busiCardDto);

        return ResponseEntity.ok(null);
    }
}
