package com.ra.web.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "acc_role", schema = "warehouse_manager")
@IdClass(value = AccRoleEntityPK.class)
public class AccRoleEntity {
    @Id
    @Column(name = "acc_Id", nullable = false)
    private int accId;

    @Id
    @Column(name = "role_Id", nullable = false)
    private int roleId;
    @ManyToOne(targetEntity = RoleEntity.class)
    @JoinColumn(referencedColumnName = "id")
    private RoleEntity role;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccRoleEntity that = (AccRoleEntity) o;
        return accId == that.accId && roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accId, roleId);
    }
}
