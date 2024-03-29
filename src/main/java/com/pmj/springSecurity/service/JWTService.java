package com.pmj.springSecurity.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUsername(String jwt);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String jwt, UserDetails userDetails);
}
