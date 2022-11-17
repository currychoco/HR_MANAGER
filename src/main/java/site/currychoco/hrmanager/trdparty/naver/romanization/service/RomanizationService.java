package site.currychoco.hrmanager.trdparty.naver.romanization.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.currychoco.hrmanager.trdparty.naver.romanization.domain.RomanizationDto;
import site.currychoco.hrmanager.trdparty.naver.romanization.domain.RomanizationResponseDto;

@Service
public class RomanizationService {

    final String API_URL = "https://openapi.naver.com/v1/krdict/romanization";
    final String API_CLIENT_ID = "TwQE_ko4XxQaNZEtHWp8";
    final String API_CLIENT_SECRET = "FuDCt1nS3g";

    public RomanizationDto getRecommandRomanization(String korName) {

        final String url = String.format("%s?query=%s", API_URL, korName);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", API_CLIENT_ID);
        headers.set("X-Naver-Client-Secret", API_CLIENT_SECRET);

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<RomanizationResponseDto> response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                request,
                RomanizationResponseDto.class);

        return response.getBody().getResult().get(0); // TODO: 인덱스 오류 수정 필요함
    }
}
