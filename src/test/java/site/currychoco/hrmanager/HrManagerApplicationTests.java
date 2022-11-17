package site.currychoco.hrmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.currychoco.hrmanager.trdparty.naver.romanization.domain.RomanizationDto;
import site.currychoco.hrmanager.trdparty.naver.romanization.service.RomanizationService;

@SpringBootTest
class HrManagerApplicationTests {

    @Autowired
    RomanizationService romanizationService;

    @Test
    void 네이버_RestTemplate_테스트() {

        final String query = "이혜량";
        RomanizationDto result = romanizationService.getRecommandRomanization(query);
        System.out.println(result);
    }
}
