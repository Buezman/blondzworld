package com.buezman.blondzworld.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String gender;
}
