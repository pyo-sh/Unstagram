package co.kr.datapia.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@Table(name="tb_board_pic")
public class BoardPicture {
    @Id
    @GeneratedValue
    private Integer idx;
    //@JsonIgnore
    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="board_idx")
    private Board board;

    // 원본 파일이름 과 서버에 저장된 파일 경로 를 분리한 이유?
    // 동일한 이름을 가진 파일이 업로드가 된다면 오류가 생긴다.
    // 이를 해결하기 위함
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private String originalFileName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private String storedFilePath;

    private long fileSize;
}
