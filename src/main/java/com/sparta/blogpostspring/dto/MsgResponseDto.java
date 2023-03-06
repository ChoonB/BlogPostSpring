package com.sparta.blogpostspring.dto;

import lombok.Getter;

@Getter
public class MsgResponseDto {
    private String msg;
    private int statusCode;
    public MsgResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
