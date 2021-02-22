package co.kr.datapia.model.request;

import lombok.Data;

@Data
public class SessionRequestDto {
    private Integer idx;

    private String userId;

    private String password;
}