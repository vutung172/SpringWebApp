package com.ra.web.service.Impl;

import com.ra.web.model.dto.request.AuthenticationRequest;
import com.ra.web.model.dto.request.RegisterRequest;
import com.ra.web.model.dto.adapter.AccAdapter;
import com.ra.web.model.entity.EmployeeDetailEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.repository.EmployeeRepository;
import com.ra.web.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccRepository accRepository;
    private final EmployeeRepository employeeRepository;
    private final AccRoleRepository accRoleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(RegisterRequest registration) {
        AccEntity acc = new AccEntity();
        EmployeeDetailEntity accDetail = new EmployeeDetailEntity();


        if (accRepository.findByUserName(registration.getUserName()).isEmpty()){
            acc.setUserName(registration.getUserName());
            acc.setPassword(registration.getPassword());
            acc.setEmail(registration.getEmail());
            acc.setAccStatus(true);
            accRepository.save(acc);

            AccRoleEntity accRole = new AccRoleEntity(acc.getAccId(), 3);
            accRoleRepository.save(accRole);

            accDetail.setAccId(acc.getAccId());
            accDetail.setEmployeeName(registration.getFirstName()+" "+registration.getLastName());
            accDetail.setEmail(registration.getEmail());
            accDetail.setPhone(registration.getPhone());
            accDetail.setAddress(registration.getAddress());
            accDetail.setEmployeeId(registration.getEmployeeId());
            employeeRepository.save(accDetail);

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

    public boolean logout(AccEntity acc){
        return true;
    }
}
