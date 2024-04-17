package com.ra.web.model.entity;

import com.ra.web.model.dto.BillDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bills", schema = "warehouse_manager")
public class BillEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Bill_id")
    private Long billId;
    @Basic
    @Column(name = "Bill_Code")
    private String billCode;
    @Basic
    @Column(name = "Bill_Type")
    private Boolean billType;
    @Basic
    @Column(name = "Emp_id_created")
    private String empIdCreated;
    @Basic
    @Column(name = "Created")
    private Date created;
    @Basic
    @Column(name = "Emp_id_auth")
    private String empIdAuth;
    @Basic
    @Column(name = "Auth_date")
    private Date authDate;
    @Basic
    @Column(name = "Bill_Status")
    private Short billStatus;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "billId")
    private List<BillDetailsEntity> billDetails;

}
