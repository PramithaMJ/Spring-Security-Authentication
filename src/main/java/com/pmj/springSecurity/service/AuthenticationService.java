package com.pmj.springSecurity.service;

import com.pmj.springSecurity.dto.JwtAuthenticationResponseDto;
import com.pmj.springSecurity.dto.RefreshTokenRequestDto;
import com.pmj.springSecurity.dto.SignInRequestDto;
import com.pmj.springSecurity.dto.SignUpRequestDto;
import com.pmj.springSecurity.entity.User;

public interface AuthenticationService {
    User signUp(SignUpRequestDto signUpRequestDto);
    JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto);

    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
