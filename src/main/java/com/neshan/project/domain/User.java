package com.neshan.project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Set<Role> roles = new HashSet<>();
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Report> reports = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", roles=" + roles.toString() +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public Long getRoleId(String roleName) {
        for (Role role : roles
        ) {
            if (role.getName().equals(roleName)) {
                return role.getId();
            }
        }
        return -1L;
    }

    public boolean hasRole(String roleName) {
        for (Role role : roles
        ) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Long roleId) {
        Role roleForDelete = null;
        for (Role role : roles) {
            if (role.getId().equals(roleId)) {
                roleForDelete = role;
                break;
            }
        }
        if (roleForDelete != null) {
            roles.remove(roleForDelete);
        }
    }

}
