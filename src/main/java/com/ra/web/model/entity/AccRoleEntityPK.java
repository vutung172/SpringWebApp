package com.ra.web.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class AccRoleEntityPK implements Serializable {
    @Id
    @Column(name = "acc_Id", nullable = false)
    private int accId;

    @Id
    @Column(name = "role_Id", nullable = false)
    private int roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccRoleEntity that = (AccRoleEntity) o;
        return Objects.equals(accId, that.getAccId()) && Objects.equals(roleId, that.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(accId, roleId);
    }
}
