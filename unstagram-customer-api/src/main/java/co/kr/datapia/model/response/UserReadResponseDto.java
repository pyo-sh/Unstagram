package co.kr.datapia.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
public class UserReadResponseDto {
    private String userId;

    private String name;

    private String phone;

    private String introduce;

    private String website;

    private String userImagePath;
}
