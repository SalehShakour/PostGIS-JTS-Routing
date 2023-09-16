package com.neshan.project.controller;


import com.neshan.project.domain.User;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.AvailableRole;
import com.neshan.project.repository.UserRepository;
import com.neshan.project.service.RoleService;
import com.neshan.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    @PutMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN')")
    public void addAdminRole(@RequestParam String id){
        User user = userService.getUserById(id);
        roleService.addRoleToUser(user, AvailableRole.ROLE_ADMIN);

    }

    @PutMapping("/editor")
    public void addEditorRole(@RequestParam String id){
        User user = userService.getUserById(id);
        roleService.addRoleToUser(user, AvailableRole.ROLE_EDITOR);

    }
    @DeleteMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN')")
    public void removeAdminRole(@RequestParam String id){
        User user = userService.getUserById(id);
        roleService.removeRoleFromUser(user, AvailableRole.ROLE_ADMIN);

    }

    @DeleteMapping("/editor")
    public void removeEditorRole(@RequestParam String id){
        User user = userService.getUserById(id);
        roleService.removeRoleFromUser(user, AvailableRole.ROLE_EDITOR);
    }
}
