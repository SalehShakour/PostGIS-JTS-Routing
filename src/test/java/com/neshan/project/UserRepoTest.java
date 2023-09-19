package com.neshan.project;

import com.neshan.project.domain.User;
import com.neshan.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepoTest {
    @Mock
    private UserRepository userRepo;
    @Mock
    private User expected;
    @Mock
    private User notExpected;

    @Test
    void findByEmail() {
        when(userRepo.findByEmail(expected.getEmail()))
                .thenReturn(Optional.of(expected));
        assertEquals(userRepo.findByEmail(expected.getEmail()),Optional.of(expected));
        assertNotEquals(userRepo.findByEmail(expected.getEmail()),Optional.of(notExpected));
    }
    @Test
    void delete() {
        userRepo.delete(expected);
        verify(userRepo).delete(expected);
    }
}
