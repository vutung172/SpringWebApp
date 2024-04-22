package com.ra.web.model.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.web.model.dto.BillDetailDto;
import com.ra.web.model.dto.request.BillDetailRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill_details", schema = "warehouse_manager")
public class BillDetailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Bill_Detail_Id")
    private Long billDetailId;
    @Basic
    @Column(name = "Bill_Id")
    private Long billId;
    @Basic
    @Column(name = "Product_Id")
    private String productId;
    @Basic
    @Column(name = "Quantity")
    private Integer quantity;
    @Basic
    @Column(name = "Price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billId", referencedColumnName = "Bill_Id")
    private BillEntity bill;

}
