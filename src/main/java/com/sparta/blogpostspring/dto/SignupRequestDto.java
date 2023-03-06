package com.sparta.blogpostspring.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}", message = "유저 이름은 최소 4자 이상, 10자 이하이며 알파벳 소문자와 숫자만 사용해야 합니다.")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}", message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자와 숫자만 사용해야 합니다.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
