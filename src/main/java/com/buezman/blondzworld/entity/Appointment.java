package com.buezman.blondzworld.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name = "appointments")
public class Appointment {

    private Long id;
    private User user;
    private LocalDateTime createdAt;
}
