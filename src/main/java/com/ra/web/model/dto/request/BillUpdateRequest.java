package com.ra.web.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillUpdateRequest {
    private int billId;
    private List<BillDetailRequest> billDetails;
}
