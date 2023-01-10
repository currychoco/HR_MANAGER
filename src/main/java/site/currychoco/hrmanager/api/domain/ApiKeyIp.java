package site.currychoco.hrmanager.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "api_key_ip")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ApiKeyIp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="ip_address")
    private String ipAddress;
    @Column(name="api_key")
    private String apiKey;
}
