package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.LoginRequestDto;
import com.sparta.blogpostspring.dto.SignupRequestDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    // 로그인페이지 Spring Security써서 한번 구현 해봄
//    @GetMapping("/login-page")
//    public ModelAndView loginPage() {
//        return new ModelAndView("login");
//    }

    @PostMapping("/signup")
    public MessageResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public MessageResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }




}
