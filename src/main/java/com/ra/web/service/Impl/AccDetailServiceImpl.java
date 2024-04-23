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
@NoArgsConstructor
@AllArgsConstructor
public class AccDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccRepository accRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccEntity acc = accRepository.findByUserName(username).orElse(null);
        if (acc != null){
            return new AccAdapter(acc);
        }
        throw new UsernameNotFoundException("Username \"" + username + "\" not found!");
    }
}
