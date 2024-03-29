package com.pmj.springSecurity.service.impl;

import com.pmj.springSecurity.dto.JwtAuthenticationResponseDto;
import com.pmj.springSecurity.dto.RefreshTokenRequestDto;
import com.pmj.springSecurity.dto.SignInRequestDto;
import com.pmj.springSecurity.dto.SignUpRequestDto;
import com.pmj.springSecurity.entity.Role;
import com.pmj.springSecurity.entity.User;
import com.pmj.springSecurity.repository.UserRepository;
import com.pmj.springSecurity.service.AuthenticationService;
import com.pmj.springSecurity.service.JWTService;
import com.pmj.springSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    public User signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName(signUpRequestDto.getLastName());
        user.setEmail(signUpRequestDto.getEmail());
        user.setRoles(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.getEmail(),
                        signInRequestDto.getPassword()
                )
        );
        var user = userRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();

        jwtAuthenticationResponseDto.setAccessToken(jwt);
        jwtAuthenticationResponseDto.setRefreshToken(refreshToken);

        return jwtAuthenticationResponseDto;

    }

    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String userEmail = jwtService.extractUsername(refreshTokenRequestDto.getAccessToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequestDto.getAccessToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();

            jwtAuthenticationResponseDto.setAccessToken(jwt);
            jwtAuthenticationResponseDto.setRefreshToken(refreshTokenRequestDto.getAccessToken());

            return jwtAuthenticationResponseDto;

        }

        return null;
    }
}