package site.currychoco.hrmanager.trdparty.naver.romanization.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // json에서 모르는 속성은 무시하겠습니다(기본은 오류 발생)
public class RomanizationResponseDto {

    @JsonProperty(value = "aResult")
    private List<RomanizationDto> result;
}
