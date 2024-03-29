package com.pmj.springSecurity.controller;

import com.pmj.springSecurity.dto.JwtAuthenticationResponseDto;
import com.pmj.springSecurity.dto.RefreshTokenRequestDto;
import com.pmj.springSecurity.dto.SignInRequestDto;
import com.pmj.springSecurity.dto.SignUpRequestDto;
import com.pmj.springSecurity.entity.User;
import com.pmj.springSecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequestDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequestDto));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthenticationResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDto));
    }

}
