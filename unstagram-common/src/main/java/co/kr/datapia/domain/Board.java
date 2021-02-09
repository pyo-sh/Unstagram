package co.kr.datapia.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    private Integer ID;

    @NotEmpty
    private String user;

    @Setter
    private String reported_date;

    @Setter
    @NotEmpty
    private String content;

    // TODO : List 형태는 Transient 로 DB 처리를 하지 않았다. 나중에 class 로 빼내어서 처리
    @Setter
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BoardPicture> pictures;
}
