package site.currychoco.hrmanager.englishName.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.trdparty.naver.romanization.domain.RomanizationDto;
import site.currychoco.hrmanager.trdparty.naver.romanization.service.RomanizationService;

@Controller
public class EnglishNameController {

    @Autowired
    private RomanizationService romanizationService;

    @GetMapping("/englishname")
    public String englishName(){return "englishname/recommendName";}

    @ResponseBody
    @GetMapping("/englishname/recommand")
    public RomanizationDto recommandEnglishName(@RequestParam(name = "korName") String korName) {
        RomanizationDto result = romanizationService.getRecommandRomanization(korName);
        return result;
    }
}
