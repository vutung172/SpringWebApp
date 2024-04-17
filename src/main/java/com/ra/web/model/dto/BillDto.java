package com.ra.web.model.dto;

import com.ra.web.Enum.ConstStatus;
import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.EmployeeRepository;
import com.ra.web.service.Impl.EmployeeServiceImpl;
import com.ra.web.service.Impl.ProductService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
