package com.buezman.blondzworld.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;
    private String username;
    private String role;
    private String gender;
}
