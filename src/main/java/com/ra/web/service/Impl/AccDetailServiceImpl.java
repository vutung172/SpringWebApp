package com.ra.web.service.Impl;

import com.ra.web.model.dto.AccAdapter;
import com.ra.web.model.entity.AccEntity;
import com.ra.web.repository.AccRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccDetailServiceImpl implements UserDetailsService {
    private final AccRepository accRepository;
    private final AccAdapter accAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccEntity acc = accRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        accAdapter.setAccEntity(acc);
        return accAdapter;
    }
}
