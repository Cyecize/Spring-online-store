package com.cyecize.skatefixers.areas.users.entities;

import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "authority", nullable = false, unique = true)
    private UserRoleType authority;

    public UserRole() {
    }

    @Override
    @Transient
    public String getAuthority() {
        return this.authority.name();
    }

    public void setAuthority(UserRoleType authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.authority.name();
    }
}
