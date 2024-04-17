package com.ra.web.model.dto.request;

import com.ra.web.model.entity.BillEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalRequest {
    private int billId;
    private Short billStatus;
}
