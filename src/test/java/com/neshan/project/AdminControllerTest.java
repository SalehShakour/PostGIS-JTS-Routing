package com.neshan.project;

import com.neshan.project.config.JwtService;
import com.neshan.project.controller.AdminController;
import com.neshan.project.domain.Report;
import com.neshan.project.domain.Role;
import com.neshan.project.domain.User;
import com.neshan.project.dto.UserDTO;
import com.neshan.project.dto.mapper.MapStructUser;
import com.neshan.project.myEnum.AvailableRole;
import com.neshan.project.repository.UserRepository;
import com.neshan.project.service.ReportService;
import com.neshan.project.service.RoleService;
import com.neshan.project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    private static final String USER_EMAIL = "test@email";
    private static final String USER_FIRSTNAME = "saleh";
    private static final String USER_LASTNAME = "shakour";
    private static final String PASSWORD = "1234";


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MapStructUser mapStructUser;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private ReportService<Report> reportService;
    @MockBean
    private RoleService roleService;
    @MockBean
    private JwtService jwtService;


    @BeforeEach
    public void setUp() {
        User user = createUser();
        UserDTO userDTO = createUserDTO();

        Mockito.when(mapStructUser.userToUserDTO(user)).thenReturn(userDTO);
        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    public void getPendingTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pending")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private User createUser() {
        User user = new User(1L, USER_EMAIL,new HashSet<>(), USER_FIRSTNAME,
                USER_LASTNAME,PASSWORD,new ArrayList<>());
        Set<Role> userRoles = user.getRoles();
        userRoles.add(new Role(AvailableRole.ROLE_EDITOR, user));
        user.setRoles(userRoles);
        return user;
    }

    private UserDTO createUserDTO() {
        return new UserDTO(USER_EMAIL, USER_FIRSTNAME, USER_LASTNAME);
    }

}

