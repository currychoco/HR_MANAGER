package site.currychoco.hrmanager.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "api_key_auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ApiKeyAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="method")
    private String method;
    @Column(name="uri")
    private String uri;
    @Column(name="api_key")
    private String apiKey;
}
