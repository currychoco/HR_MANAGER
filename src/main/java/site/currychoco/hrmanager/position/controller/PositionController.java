package site.currychoco.hrmanager.position.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.position.domain.PositionDto;
import site.currychoco.hrmanager.position.service.PositionService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ResponseBody
    @GetMapping("/position/all/read")
    public List<PositionDto> getAllPosition(){
        return positionService.getAllPosition();
    }
}
