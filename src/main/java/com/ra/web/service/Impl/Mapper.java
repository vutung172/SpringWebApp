package com.ra.web.service.Impl;

import com.ra.web.Enum.ConstStatus;
import com.ra.web.model.dto.BillDetailDto;
import com.ra.web.model.dto.BillDto;
import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.BillRepository;
import com.ra.web.repository.EmployeeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Mapper {
    @Autowired
    private final EmployeeServiceImpl employeeService;
    @Autowired
    private final EmployeeRepository employeeRepository;

    public BillDto fromBillEntity(BillEntity bill){

        return BillDto.builder()
                .billId(bill.getBillId())
                .billCode(bill.getBillCode())
                .billType(bill.getBillType()?"Import":"Export")
                .empCreated(employeeService.findNameByEmpId(bill.getEmpIdCreated()))
                .created(bill.getCreated())
                .empAuth(employeeService.findNameByEmpId(bill.getEmpIdAuth()))
                .authDate(bill.getAuthDate())
                .billStatus(bill.getBillStatus() == ConstStatus.BillStt.CREATE ?"CREATE" : bill.getBillStatus() == ConstStatus.BillStt.APPROVAL? "APPROVAL" : "CANCEL")
                .billDetails(bill.getBillDetails().stream().map(this::fromBillDetailEntity).collect(Collectors.toList()))
                .build();

    }
    public BillDetailsEntity toBillDetailEntity(BillDetailRequest request) {
        return BillDetailsEntity.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }

    public BillDetailDto fromBillDetailEntity(BillDetailsEntity entity) {
        ProductService service = new ProductService();
        return BillDetailDto.builder()
                .billDetailId(entity.getBillDetailId())
                .productName(service.findNameById(entity.getProductId()))
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }
    public BillDto toBill(BillEntity bill){
        BillDto billDto = new BillDto();
        return billDto;
    }

    public BillDetailDto toBillDetail(BillDetailsEntity billDetails){
        return fromBillDetailEntity(billDetails);
    }
}
