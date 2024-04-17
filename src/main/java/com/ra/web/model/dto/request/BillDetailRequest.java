package com.ra.web.model.dto.request;

import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.ra.web.model.entity.BillDetailsEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDetailRequest{
    private String productId;
    private Integer quantity;
    private double price;

}
