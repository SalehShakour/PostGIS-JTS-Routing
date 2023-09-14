package com.neshan.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
