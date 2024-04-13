package com.ra.web.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acc_role", schema = "warehouse_manager")
@IdClass(AccRoleEntityPK.class)
public class AccRoleEntity {
    @Id
    @Column(name = "Acc_Id")
    private Integer accId;

    @Id
    @Column(name = "Role_Id")
    private Integer roleId;

    @Basic
    @Column(name = "Role_Acc_id")
    private Integer roleAccId;

    @ManyToOne
    @JoinColumn(name = "Role_Id", referencedColumnName = "Role_Id", nullable = false)
    private RoleEntity rolesByRoleId;

    @ManyToOne
    @JoinColumn(name = "Acc_Id", referencedColumnName = "Acc_Id", nullable = false)
    private AccEntity accByUserId;
}
