package site.currychoco.hrmanager.ownInformation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.currychoco.hrmanager.ownInformation.domain.OwnInformation;
import site.currychoco.hrmanager.ownInformation.domain.OwnInformationDto;
import site.currychoco.hrmanager.ownInformation.service.OwnInformationService;

@RequiredArgsConstructor
@Controller
public class OwnInformationController {

    private final OwnInformationService ownInformationService;

    // ---
    // page
    // ---

    // ---
    // api
    // ---
    @PostMapping("/onw-info/create")
    public void createOwnInfo(@RequestBody OwnInformationDto dto){
        ownInformationService.saveOwnInfo(dto);
    }
}
