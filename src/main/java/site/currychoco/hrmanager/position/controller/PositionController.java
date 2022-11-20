package site.currychoco.hrmanager.position.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @GetMapping("/manager/position/create")
    public String createPosition(){return "manager/position/addPosition";}


    // ---
    // api
    // ---

    /**
     * 직급 생성
     */
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

}
