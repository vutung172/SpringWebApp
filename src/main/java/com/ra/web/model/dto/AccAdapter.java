package com.ra.web.model.dto;

import com.ra.web.model.entity.AccEntity;
import com.ra.web.model.entity.AccRoleEntity;
import com.ra.web.model.entity.RoleEntity;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.repository.RoleRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Setter
@Getter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class AccAdapter implements UserDetails{
    private AccEntity accEntity;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        List<RoleEntity> accRoles = accEntity.getRole().stream().map(AccRoleEntity::getRole).toList();
        if(!accRoles.isEmpty()){
            for (RoleEntity role : accRoles){
                roles.add(new SimpleGrantedAuthority(String.valueOf(role.getName())));
            }
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return accEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return accEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return accEntity.getAccStatus();
    }
}
