package co.kr.datapia.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardPicture {
    @Id
    @GeneratedValue
    private Integer idx;
    @NotNull
    private Integer boardIdx;

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
