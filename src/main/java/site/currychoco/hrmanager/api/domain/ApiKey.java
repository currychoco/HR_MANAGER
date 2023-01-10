package site.currychoco.hrmanager.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_key")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ApiKey {
    @Id
    @Column(name="api_key")
    private String apiKey;
    @Column(name="description")
    private String description;
}
