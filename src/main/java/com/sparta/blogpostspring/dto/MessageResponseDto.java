package com.sparta.blogpostspring.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MessageResponseDto {
    private String msg;
    private HttpStatus status;
    private int statusCode;

    public MessageResponseDto(String msg,HttpStatus httpStatus) {
        this.msg = msg;
        this.status = httpStatus;
        this.statusCode = httpStatus.value();
    }
}
