package com.oched.booksprj.entities;

import com.oched.booksprj.enumerations.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String email;
    private String password;
    @CreationTimestamp
    private Date registeredOn;
    private boolean auth = true;
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    public UserEntity(String login, String email, String password, Set<UserRole> roles) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
