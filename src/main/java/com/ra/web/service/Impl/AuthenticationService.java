package com.ra.web.service.Impl;

import com.ra.web.model.dto.AuthenticationRequest;
import com.ra.web.model.dto.RegisterRequest;
import com.ra.web.model.dto.AccAdapter;
import com.ra.web.model.entity.UserDetailEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.repository.AccDetailsRepository;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccRepository accRepository;
    private final AccDetailsRepository accDetailsRepository;
    private final AccRoleRepository accRoleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    public String register(RegisterRequest registration) {
        AccEntity acc = new AccEntity();
        UserDetailEntity accDetail = new UserDetailEntity();


        if (accRepository.findByUserName(registration.getUserName()).isEmpty() && accDetailsRepository.findAllByEmail(registration.getEmail()).isEmpty()){
            acc.setUserName(registration.getUserName());
            acc.setPassword(registration.getPassword());
            acc.setEmail(registration.getEmail());
            acc.setAccStatus(true);
            accRepository.save(acc);

            AccRoleEntity accRole = new AccRoleEntity(acc.getAccId(), 3);
            accRoleRepository.save(accRole);

            accDetail.setAccId(acc.getAccId());
            accDetail.setUserName(registration.getFirstName()+" "+registration.getLastName());
            accDetail.setEmail(registration.getEmail());
            accDetail.setPhone(registration.getPhone());
            accDetail.setAddress(registration.getAddress());
            accDetailsRepository.save(accDetail);

            var user = AccAdapter.builder()
                    .accEntity(acc)
                    .build();


            var jwtToken = jwtUtil.generateTokenAndEmail(user);
            return String.valueOf(jwtToken);
        } else {
            return null;
        }
    }

    public String authenticate(AuthenticationRequest authentication) {
        String password = passwordEncoder.encode(authentication.getPassword());
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
            var jwtToken = jwtUtil.generateTokenAndEmail(accAdapter);
            return String.valueOf(jwtToken);
        } else {
            return null;
        }
    }
}
