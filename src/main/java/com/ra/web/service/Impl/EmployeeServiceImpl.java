package com.ra.web.service.Impl;

import com.ra.web.model.entity.EmployeeDetailEntity;
import com.ra.web.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl {
    private final EmployeeRepository employeeRepository;

    public String findNameByEmpId(String id){
        EmployeeDetailEntity empl = employeeRepository.findEmployeeDetailEntityByEmployeeId(id);
        if (empl != null){
            return empl.getEmployeeName();
        } else {
            return "";
        }
    }
    public EmployeeDetailEntity create(){
        return null;
    }
}
