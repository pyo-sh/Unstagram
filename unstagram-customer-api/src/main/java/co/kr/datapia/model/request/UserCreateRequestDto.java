package co.kr.datapia.model.request;

import lombok.Data;

@Data
public class UserCreateRequestDto {
    private String phone;

    private String userId;

    private String name;

    private String password;
}