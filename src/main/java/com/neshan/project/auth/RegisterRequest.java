package com.neshan.project.auth;


import com.neshan.project.domain.Role;
import com.neshan.project.repository.RoleRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
