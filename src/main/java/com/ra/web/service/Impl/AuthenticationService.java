package com.ra.web.service.Impl;

import com.ra.web.model.dto.AuthenticationRequest;
import com.ra.web.model.dto.RegisterRequest;
import com.ra.web.model.dto.AccAdapter;
import com.ra.web.model.entity.AccDetailEntity;
import com.ra.web.model.entity.AccRoleEntity;
import com.ra.web.model.entity.AccEntity;
import com.ra.web.repository.AccDetailsRepository;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    public String register(RegisterRequest registration) {
        AccEntity acc = new AccEntity();
        AccDetailEntity accDetail = new AccDetailEntity();
        AccRoleEntity accRole = new AccRoleEntity();

        if (accRepository.findByUserName(registration.getUserName()).isEmpty() && accDetailsRepository.findAllByEmail(registration.getEmail()).isEmpty()){
            acc.setUserName(registration.getUserName());
            acc.setPassword(registration.getPassword());
            acc.setAccStatus(true);
            accRepository.save(acc);

            accRole.setAccId(acc.getAccId());
            accRole.setRoleId(3);
            accRoleRepository.save(accRole);

            accDetail.setAccId(acc.getAccId());
            accDetail.setUserName(registration.getFirstName()+" "+registration.getLastName());
            accDetail.setEmail(registration.getEmail());
            accDetail.setPhone(registration.getPhone());
            accDetail.setAddress(registration.getAddress());
            /*accDetailsRepository.save(accDetail);*/

            var user = AccAdapter.builder()
                    .accEntity(acc)
                    .build();


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
