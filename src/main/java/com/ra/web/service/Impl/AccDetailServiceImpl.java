package com.ra.web.service.Impl;

import com.ra.web.model.dto.adapter.AccAdapter;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.repository.AccRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AccDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccRepository accRepository;
    @Autowired
    private AccAdapter accAdapter;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccEntity acc = accRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        accAdapter.setAccEntity(acc);
        return accAdapter;
    }
}
