package com.sparta.blogpostspring.exeption;

import com.sparta.blogpostspring.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    throw로 발생시킨 오류 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponseDto> handleRuntimeException(RuntimeException e){
        MessageResponseDto messageResponseDto = new MessageResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(messageResponseDto, messageResponseDto.getStatus());
    }

//    @Valid 오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponseDto> handleNotValidException(MethodArgumentNotValidException e){
        String message = e.getFieldError().getDefaultMessage();
        MessageResponseDto messageResponseDto = new MessageResponseDto(message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(messageResponseDto, messageResponseDto.getStatus());
    }



}
