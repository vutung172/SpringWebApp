package com.ra.web.service.Impl;

import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.BillDetailRepository;
import com.ra.web.util.Mapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BillDetailServiceImpl {

    private BillDetailRepository billDetailRepository;
    private Mapper mapper;
    public BillDetailsEntity add(BillEntity bill, BillDetailRequest request){
        BillDetailsEntity billDetails = mapper.convertDTOToEntity(request,BillDetailsEntity.class);
        billDetails.setBillId(bill.getBillId());
        BillDetailsEntity createdBD =  billDetailRepository.save(billDetails);
        return billDetailRepository.findById(Integer.parseInt(String.valueOf(createdBD.getBillDetailId()))).orElse(null);
    }
    public BillDetailsEntity update(BillDetailsEntity billDetails) {
        BillDetailsEntity updateBillDetail = billDetailRepository.findById(Integer.parseInt(String.valueOf(billDetails.getBillDetailId()))).orElse(null);
        if (updateBillDetail != null) {
            updateBillDetail = billDetails;
            return billDetailRepository.save(updateBillDetail);
        }
        return null;
    }

    public List<BillDetailsEntity> findByBillId(Long billId){
        return billDetailRepository.findAllByBillId(billId);
    }
}
