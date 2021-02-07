package co.kr.datapia.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer idx;

    // TODO : email 과 phone 을 Identification 으로 사용하는 방법은 없을까?
    @NotEmpty
    private String email;
    @Setter
    private String phone;

    @NotEmpty
    private String password;

    @Setter
    private String name;
    @Setter
    private String introduce;
}
