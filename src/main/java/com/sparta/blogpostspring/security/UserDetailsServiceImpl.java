package com.sparta.blogpostspring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.entity.User;
import com.sparta.blogpostspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UserRepository userRepository;

//    유저가 없을때 예외처리는 어떻게 하면 좋을까? 서버의 로그창엔뜨는데 클라이언트 한테는 안뜬다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.")
        );
        return new UserDetailsImpl(user,user.getUsername());

    }

}
