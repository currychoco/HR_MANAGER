package site.currychoco.hrmanager.trdparty.naver.romanization.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RomanizationItemDto {

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "score")
    private int score;
}
