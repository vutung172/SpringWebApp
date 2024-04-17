package com.ra.web.service.Impl;

import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.BillDetailRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl {
    @Autowired
    private BillDetailRepository billDetailRepository;
    private final Mapper mapper;
    public BillDetailsEntity add(BillEntity bill, BillDetailRequest request){
        BillDetailsEntity billDetails = mapper.toBillDetailEntity(request);
        billDetails.setBillId(bill.getBillId());
        return billDetailRepository.save(billDetails);
    }
}
