package co.kr.datapia.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_user")
public class User {
    @Id
    @GeneratedValue
    private Integer idx;

    // TODO : email 과 phone 을 Identification 으로 사용하는 방법은 없을까?
    @NotEmpty(message = "ID 를 입력해 주세요")
    private String userId;

    private String password;
    @Setter
    @NotEmpty
    private String name;

    @Setter
    private String introduce;
    @Setter
    private String website;
    @Setter
    private String phone;

    @Setter
    private String userImagePath;

    private String signedDate;

    @Setter
    @OneToMany(mappedBy="user")
    @JsonManagedReference
    private List<Board> boards;

    private Integer level;

    public boolean isEnable(){
        return this.level >= 1;
    }
    public void setEnable(boolean enable){
        if(enable)
            this.level = 1;
        else
            this.level = 0;
    }
}
