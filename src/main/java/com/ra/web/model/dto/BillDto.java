package com.ra.web.model.dto;

import lombok.*;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Long billId;
    private String billCode;
    private String billType;
    private String empCreated;
    private Date created;
    private String empAuth;
    private Date authDate;
    private String billStatus;
    private List<BillDetailDto> billDetails;

}
