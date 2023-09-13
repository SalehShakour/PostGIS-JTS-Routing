package com.neshan.project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
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
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", roles=" + roles.toString() +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
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
