package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.LoginRequestDto;
import com.sparta.blogpostspring.dto.SignupRequestDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.entity.User;
import com.sparta.blogpostspring.entity.UserRoleEnum;
import com.sparta.blogpostspring.jwt.JwtUtil;
import com.sparta.blogpostspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    //회원가입 메서드
    @Transactional
    public MessageResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        //회원중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            return new MessageResponseDto("중복된 사용자가 존재합니다.",HttpStatus.BAD_REQUEST);
        }

        // 사용자 Role 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
                return new MessageResponseDto("관리자 암호가 틀려 등록이 불가능합니다.",HttpStatus.BAD_REQUEST);
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, password, role);
        userRepository.save(user);

        return new MessageResponseDto("회원가입에 성공했습니다.", HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public MessageResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        //사용자 확인
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) return new MessageResponseDto("등록된 사용자가 없습니다.", HttpStatus.BAD_REQUEST);

        //비밀번호 확인
        if(!user.get().getPassword().equals(password)){
    //            return new MessageResponseDto("비밀번호가 일치하지 않습니다.", 400);
                return new MessageResponseDto("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get().getUsername(), user.get().getRole()));

        return new MessageResponseDto("로그인에 성공하였습니다.", HttpStatus.OK);
    }
}
