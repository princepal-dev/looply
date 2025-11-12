package com.princeworks.looply.security.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String username;

    public UserInfoResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
