package com.neshan.project.service;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.dto.UserDTO;
import com.neshan.project.exception.CustomException;
import com.neshan.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User userValidation(String id) {
        return userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new CustomException("User not found"));
    }

    public User getUserById(String id) {
        return userValidation(id);
    }

    public void deleteUserById(String id) {
        userRepository.delete(userValidation(id));
    }

    public void updateUserById(User currentUser, UserDTO updatedUserDTO) {
        if (userRepository.findByEmail(updatedUserDTO.email()).orElse(null) != null) {
            throw new CustomException("This email address is unavailable");
        }
        if (updatedUserDTO.email() != null) currentUser.setEmail(updatedUserDTO.email());
        if (updatedUserDTO.firstname() != null) currentUser.setFirstname(updatedUserDTO.firstname());
        if (updatedUserDTO.lastname() != null) currentUser.setLastname(updatedUserDTO.lastname());

        userRepository.save(currentUser);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

}
