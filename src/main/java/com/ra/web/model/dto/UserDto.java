package com.ra.web.model.dto;


import com.ra.web.model.entity.EmployeeDetailEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import com.ra.web.model.entity.accounts.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String userName;
    private String email;
    private Boolean permission;
    private Boolean accStatus;
    private List<RoleEntity> userRoles;
    private EmployeeDetailEntity employeeDetail;
}
