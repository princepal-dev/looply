package com.princeworks.looply.security.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class LoginRequest {
    @NotBlank
    @Size (min = 4, message = "Please provide a valid username!")
    private String userName;

    @NotBlank
    @Size (min = 8, message = "Password should be of at-least 8 characters!")
    private String password;
}
