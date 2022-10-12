package com.buezman.blondzworld.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @NotEmpty
    private String password;

    @NotEmpty
    private String username;

    @Email
    private String email;

    private String gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
