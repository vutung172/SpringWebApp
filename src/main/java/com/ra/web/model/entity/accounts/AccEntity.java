package com.ra.web.model.entity.accounts;

import com.ra.web.model.entity.EmployeeDetailEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts", schema = "warehouse_manager")
public class AccEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Acc_Id")
    private Integer accId;
    @Basic
    @Column(name = "User_name")
    private String userName;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Permission")
    private Boolean permission;
    @Basic
    @Column(name = "Acc_status")
    private Boolean accStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accByUserRoleId")
    private List<AccRoleEntity> userRoleEntities;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "accByEmp")
    private EmployeeDetailEntity employeeDetail;

    public AccEntity(Integer id) {
        this.accId = id;
    }
}
