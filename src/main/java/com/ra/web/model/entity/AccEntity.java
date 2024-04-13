package com.ra.web.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts", schema = "warehouse_manager")
public class AccEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Acc_id")
    private Integer accId;
    @Basic
    @Column(name = "User_name")
    private String userName;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "Permission")
    private Boolean permission;
    @Basic
    @Column(name = "Acc_status")
    private Boolean accStatus;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "accByUserId")
    private List<AccRoleEntity> userRoleEntities;

}
