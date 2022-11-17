package site.currychoco.hrmanager.trdparty.naver.romanization.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RomanizationDto {

    @JsonProperty(value = "sFirstName")
    private String firstName;

    @JsonProperty(value = "aItems")
    private List<RomanizationItemDto> items;
}
