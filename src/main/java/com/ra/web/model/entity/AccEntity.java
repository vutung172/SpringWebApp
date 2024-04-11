package com.ra.web.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts", schema = "warehouse_manager")
public class AccEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Acc_id")
    private int accId;
    @Basic
    @Column(name = "User_name", nullable = false, length = 30)
    private String userName;
    @Basic
    @Column(name = "Password", nullable = false, length = 30)
    private String password;
    @Basic
    @Column(name = "Permission", nullable = true)
    private Boolean permission;
    @Basic
    @Column(name = "User_id", nullable = true, length = 5)
    private String userId;
    @Basic
    @Column(name = "Acc_status", nullable = true)
    private Boolean accStatus;
    @OneToMany(targetEntity = AccRoleEntity.class)
    @JoinColumn(referencedColumnName = "acc_Id")
    private List<AccRoleEntity> role;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccEntity that = (AccEntity) o;
        return accId == that.accId && Objects.equals(userName, that.userName) && Objects.equals(password, that.password) && Objects.equals(permission, that.permission) && Objects.equals(userId, that.userId) && Objects.equals(accStatus, that.accStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accId, userName, password, permission, userId, accStatus);
    }
}
