package com.pmj.springSecurity.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {
    private String accessToken;
    private String refreshToken;
}
