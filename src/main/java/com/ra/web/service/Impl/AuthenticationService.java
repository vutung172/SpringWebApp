package com.ra.web.service.Impl;

import com.ra.web.Enum.RoleType;
import com.ra.web.api.AuthenticationRequest;
import com.ra.web.api.RegisterRequest;
import com.ra.web.model.dto.AccAdapter;
import com.ra.web.model.entity.AccDetailEntity;
import com.ra.web.model.entity.AccEntity;
import com.ra.web.model.entity.AccRoleEntity;
import com.ra.web.repository.AccDetailsRepository;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccRepository accRepository;
    private final AccDetailsRepository accDetailsRepository;
    private final AccRoleRepository accRoleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    public String register(RegisterRequest registration) {
        AccEntity acc = new AccEntity();
        AccDetailEntity accDetail = new AccDetailEntity();
        AccRoleEntity role = new AccRoleEntity();

        if (accRepository.findByUserName(registration.getUserName()).isEmpty() && accDetailsRepository.findAllByEmail(registration.getEmail()).isEmpty()){
            acc.setUserName(registration.getUserName());
            acc.setPassword(registration.getPassword());
            acc.setAccStatus(true);
            accRepository.save(acc);

            role.setAccId(acc.getAccId());
            role.setRoleId(3);
            accRoleRepository.save(role);

            List<AccRoleEntity> roles = accRoleRepository.findAllByAccId(acc.getAccId());
            acc.setRole(roles);

            accDetail.setUserName(registration.getFirstName()+" "+registration.getLastName());
            accDetail.setEmail(registration.getEmail());
            accDetail.setPhone(registration.getPhone());
            accDetail.setAddress(registration.getAddress());
            accDetailsRepository.save(accDetail);

            var user = AccAdapter.builder()
                    .build();
            user.setAccEntity(acc);

            var jwtToken = jwtUtil.generateToken(user);
            return String.valueOf(jwtToken);
        } else {
            return null;
        }
    }

    public String authenticate(AuthenticationRequest authentication) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentication.getUsername(),
                        authentication.getPassword()
                )
        );
        AccEntity acc = accRepository.findByUserNameAndPassword(authentication.getUsername(),authentication.getPassword()).orElse(null);
        if (acc != null){
            AccAdapter accAdapter = new AccAdapter();
            accAdapter.setAccEntity(acc);
            var jwtToken = jwtUtil.generateToken(accAdapter);
            return String.valueOf(jwtToken);
        } else {
            return null;
        }
    }
}
