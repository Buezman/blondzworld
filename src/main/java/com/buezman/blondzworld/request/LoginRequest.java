package com.buezman.blondzworld.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotEmpty
    private String usernameOrEmail;
    @NotEmpty
    private String password;
}
