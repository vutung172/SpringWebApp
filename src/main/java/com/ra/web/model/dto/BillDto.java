package com.ra.web.model.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;


import java.util.ArrayList;
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
    private Boolean billType;
    private String empIdCreated;
    private Date created;
    private String empIdAuth;
    private Date authDate;
    private Short billStatus;
    private List<BillDetailDto> billDetails = new ArrayList<>();
}
