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
    private Integer idx;

    @NotEmpty
    private String user;

    @Setter
    private String reportedDate;

    @Setter
    @NotEmpty
    private String content;

    @Setter
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BoardPicture> pictures;
}
