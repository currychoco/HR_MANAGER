package site.currychoco.hrmanager.position.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;
import site.currychoco.hrmanager.position.domain.PositionDto;
import site.currychoco.hrmanager.position.service.PositionService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PositionController {

    private final PositionService positionService;

    // ---
    // page
    // ---
    @CheckAuthority(authCode = "g000005")
    @GetMapping("/manager/position/create")
    public String createPosition(){return "manager/position/addPosition";}

    @CheckAuthority(authCode = "g000012")
    @GetMapping("/manager/position/search")
    public String searchPosition(){return "manager/position/searchPosition";}

    @CheckAuthority(authCode = "g000012")
    @GetMapping("/manager/position/detail")
    public String detailPosition(@RequestParam(name = "positionCode") String positionCode, Model model){
        PositionDto positionDto = positionService.getPositionByPositionCode(positionCode);

        model.addAttribute("position", positionDto);

        return "manager/position/detailPosition";
    }


    // ---
    // api
    // ---

    /**
     * 직급 생성
     */
    @CheckAuthority(authCode = "g000005")
    @ResponseBody
    @PostMapping("/position/add")
    public void addPosition(@RequestBody PositionDto dto){
        positionService.addPosition(dto);
    }

    /**
     *모든 직급 출력
     */
    @ResponseBody
    @GetMapping("/position/all/read")
    public List<PositionDto> getAllPosition(){
        return positionService.getAllPosition();
    }

    /**
     * 직급 정보 수정
     */
    @CheckAuthority(authCode = "g000008")
    @ResponseBody
    @PostMapping("/position/modify")
    public void modifyPosition(@RequestBody PositionDto positionDto){
        positionService.modifyPosition(positionDto);
    }


    /**
     *  직급 검색
     */
    @CheckAuthority(authCode = "g000012")
    @ResponseBody
    @GetMapping("/position/search")
    public List<PositionDto> searchPosition(String data){
        return positionService.getSearchedPosition(data);
    }

}
