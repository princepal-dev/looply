package com.princeworks.looply.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @Email
    @NotBlank
    @Size (max = 30, message = "Email cannot be more than 30 characters!")
    private String email;

    @Size
    @NotBlank
    @Size (min = 4, message = "Please provide a valid username!")
    private String userName;

    @NotBlank
    @Size (min = 8, message = "Password should be of at-least 8 characters!")
    private String password;
}
