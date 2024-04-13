package com.ra.web.model.dto;

import com.ra.web.model.entity.AccEntity;
import com.ra.web.model.entity.RoleEntity;
import com.ra.web.repository.RoleRepository;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Setter
@Getter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class AccAdapter implements UserDetails{
    private AccEntity accEntity;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();

        accEntity.getUserRoleEntities()
                .forEach(ur -> roles.add(new SimpleGrantedAuthority(ur.getRolesByRoleId().getName())));
        return roles;
    }

    @Override
    public String getPassword() {
        return passwordEncoder.encode(accEntity.getPassword());
    }

    @Override
    public String getUsername() {
        return accEntity.getUserName();
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
        return accEntity.getAccStatus();
    }
}
