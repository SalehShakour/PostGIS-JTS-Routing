package com.neshan.project.service;

import com.neshan.project.domain.Role;
import com.neshan.project.domain.User;
import com.neshan.project.myEnum.AvailableRole;
import com.neshan.project.repository.RoleRepository;
import com.neshan.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public void addRoleToUser(User user, AvailableRole role) {
        if (!user.hasRole(role.name())){
            Role newRole = new Role(role,user);
            roleRepository.save(newRole);
            user.addRole(newRole);
            userRepository.save(user);
        }
    }

    public void removeRoleFromUser(User user, AvailableRole role) {
        Long id = user.getRoleId(role.name());
        if (id != -1L){
            user.removeRole(id);
            roleRepository.deleteById(id);
            userRepository.save(user);
        }
    }
}
