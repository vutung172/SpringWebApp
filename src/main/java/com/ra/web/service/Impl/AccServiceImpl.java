package com.ra.web.service.Impl;

import com.ra.web.model.dto.request.RegisterRequest;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import com.ra.web.model.entity.accounts.RoleEntity;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.service.AccService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AccServiceImpl implements AccService {
    private AccRepository accRepository;
    private AccRoleRepository accRoleRepository;
    public List<AccEntity> findAll(){
        return accRepository.findAll();
    }
    public AccEntity add(RegisterRequest request){
        if (accRepository.findByUserName(request.getUserName()).isEmpty()){

        }
        return null;
    }
    public AccEntity findByUserName(String userName){
        return accRepository.findByUserName(userName).orElse(null);
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
