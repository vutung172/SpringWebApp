package com.ra.web.model.dto;

import com.ra.web.model.entity.EmployeeDetailEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccDto {
    private Integer accId;
    private String userName;
    private String password;
    private String email;
    private Boolean permission;
    private Boolean accStatus;
    private List<AccRoleEntity> userRoleEntities;
}
