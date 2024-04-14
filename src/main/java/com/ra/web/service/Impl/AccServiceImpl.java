package com.ra.web.service.Impl;

import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import com.ra.web.model.entity.accounts.RoleEntity;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.service.AccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccServiceImpl implements AccService {
    @Autowired
    private AccRepository accRepository;
    @Autowired
    private AccRoleRepository accRoleRepository;


    public List<AccEntity> findAll(){
        return accRepository.findAll();
    }
    public AccEntity setRole(AccEntity acc,RoleEntity role){
        AccEntity accSetRole = accRepository.findById(acc.getAccId()).orElse(null);
        AccRoleEntity accRole = new AccRoleEntity();
        if (accSetRole != null) {
            List<AccRoleEntity> accRoles = accRoleRepository.findAllByAccId(acc.getAccId());
            accRole.setAccId(acc.getAccId());
            accRole.setRoleId(role.getRoleId());
            accRoleRepository.deleteAll(accRoles);
            accRoleRepository.save(accRole);
        }
        return accSetRole;
    }


}
