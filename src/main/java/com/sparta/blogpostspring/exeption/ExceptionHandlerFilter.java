package com.sparta.blogpostspring.exeption;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request,response);
        }catch(UsernameNotFoundException e) {
            jwtExceptionHandler(response,e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e){
            jwtExceptionHandler(response,e.getMessage().split(":")[0], HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            jwtExceptionHandler(response,e.getMessage().split(":")[0], HttpStatus.BAD_REQUEST);
        }
    }

    public void jwtExceptionHandler(HttpServletResponse response, String msg, HttpStatus httpStatus) {
        response.setStatus(httpStatus.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(new MessageResponseDto(msg,httpStatus));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
