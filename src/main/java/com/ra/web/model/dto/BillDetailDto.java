package com.ra.web.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailDto {
    private Long billDetailId;
    private String productId;
    private Integer quantity;
    private Double price;
}
