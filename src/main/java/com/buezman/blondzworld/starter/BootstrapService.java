package com.buezman.blondzworld.starter;

import com.buezman.blondzworld.entity.Role;
import com.buezman.blondzworld.entity.User;
import com.buezman.blondzworld.enums.Gender;
import com.buezman.blondzworld.enums.UserRole;
import com.buezman.blondzworld.exception.ResourceNotFoundException;
import com.buezman.blondzworld.repository.RoleRepository;
import com.buezman.blondzworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    private void loadDatabase() {
        long count = roleRepository.count();
        if (count == 0) {
            log.info("Inserting roles on startup");
            Role adminRole = Role.builder()
                    .name(UserRole.ADMIN)
                    .build();
            Role manager = Role.builder()
                    .name(UserRole.MANAGER)
                    .build();
            Role staff = Role.builder()
                    .name(UserRole.STAFF)
                    .build();
            Role client = Role.builder()
                    .name(UserRole.CLIENT)
                    .build();
            roleRepository.saveAll(Set.of(adminRole, manager, staff, client));
        }

        User user = userRepository.findUserByUsername("admin");

        if (user == null) {
            Role role = roleRepository.findRoleByName("ADMIN").orElse(null);
            User admin = User.builder()
                    .email("admin@gmail.com")
                    .firstname("admin")
                    .lastname("admin")
                    .password(passwordEncoder.encode("admin"))
                    .gender(Gender.FEMALE)
                    .username("admin")
                    .roles(Collections.singleton(role))
                    .build();

            userRepository.save(admin);
        }
    }


}
