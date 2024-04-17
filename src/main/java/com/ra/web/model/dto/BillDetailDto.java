package com.ra.web.model.dto;

import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.service.Impl.ProductService;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BillDetailDto {
    private Long billDetailId;
    private String productName;
    private Integer quantity;
    private Double price;


}
