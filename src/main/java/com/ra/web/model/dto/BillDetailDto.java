package com.ra.web.model.dto;

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
