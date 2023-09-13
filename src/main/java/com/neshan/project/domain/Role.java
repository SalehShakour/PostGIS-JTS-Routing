package com.neshan.project.domain;

import com.neshan.project.myEnum.AvailableRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Role(AvailableRole roleEnum,User user) {
        this.name = roleEnum.name();
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", userID='" + user.getEmail() +
                '}';
    }
}
