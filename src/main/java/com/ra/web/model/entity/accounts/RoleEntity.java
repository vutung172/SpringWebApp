package com.ra.web.model.entity.accounts;

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
@Table(name = "role", schema = "warehouse_manager")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id")
    private Integer roleId;
    @Basic
    @Column(name = "name")
    private String name;
    public RoleEntity(Integer id) {
        this.roleId = id;
    }

}
