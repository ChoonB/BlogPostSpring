package com.sparta.blogpostspring.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // request의 header에서 토큰을 가져옴
        String token = jwtUtil.resolveToken(request);
        // token 유효성 검사
        if (token != null) {
            if (!jwtUtil.validateToken(token)){
                jwtExceptionHandler(response, "토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);
                return;
            }
            Claims info = jwtUtil.getUserInfoFromToken(token);
            // 인증 객체 생성
            setAuthentication(info.getSubject());
        }
        // 다음 필터로 넘김
        filterChain.doFilter(request,response);
    }

//    SecurityContextHolder안에 인증객체 넣음
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

//    토큰 오류 발생시 예외처리
    public void jwtExceptionHandler(HttpServletResponse response, String msg, HttpStatus httpStatus) {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new MessageResponseDto(msg,httpStatus));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }



}
