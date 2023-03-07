package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.LoginRequestDto;
import com.sparta.blogpostspring.dto.SignupRequestDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        MessageResponseDto msg = userService.signup(signupRequestDto);
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        MessageResponseDto msg = userService.login(loginRequestDto, response);
        return new ResponseEntity<>(msg, msg.getStatus());
    }




}
