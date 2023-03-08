package com.sparta.blogpostspring.exeption;

import com.sparta.blogpostspring.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponseDto> handleRuntimeException(RuntimeException e){
        MessageResponseDto messageResponseDto = new MessageResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(messageResponseDto, messageResponseDto.getStatus());
    }
}
