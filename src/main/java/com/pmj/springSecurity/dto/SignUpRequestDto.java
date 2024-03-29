package com.pmj.springSecurity.dto;

import com.pmj.springSecurity.entity.Role;
import lombok.Data;

@Data
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
