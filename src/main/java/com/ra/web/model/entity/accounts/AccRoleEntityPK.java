package com.ra.web.model.entity.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter
public class AccRoleEntityPK implements Serializable {
    @Id
    @Column(name = "Acc_Id")
    private Integer accId;
    @Id
    @Column(name = "Role_Id")
    private Integer roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccRoleEntityPK that = (AccRoleEntityPK) o;
        return Objects.equals(accId, that.accId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accId, roleId);
    }
}
