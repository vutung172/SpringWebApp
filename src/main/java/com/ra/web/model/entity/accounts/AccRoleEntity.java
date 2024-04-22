package com.ra.web.model.entity.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleById",referencedColumnName = "Role_Id")
    private RoleEntity rolesByRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accById",referencedColumnName = "Acc_Id")
    private AccEntity accByUserRoleId;

    public AccRoleEntity(Integer accId,Integer roleId) {
        this.accId = accId;
        this.roleId = roleId;
        this.rolesByRoleId = new RoleEntity(roleId);
        this.accByUserRoleId = new AccEntity(accId);
    }
}
