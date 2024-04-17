package com.ra.web.service.Impl;

import com.ra.web.Enum.ConstStatus;
import com.ra.web.model.dto.request.ApprovalRequest;
import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.dto.request.BillUpdateRequest;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.BillDetailRepository;
import com.ra.web.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class BillServiceImpl {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailServiceImpl billDetailService;

    public BillEntity findById(long id) {
        return billRepository.findById((int)id).orElse(null);
    }

    public BillEntity create(String employeeId, Boolean billType) {
        BillEntity bill = new BillEntity();
        bill.setBillCode(("HD" + System.currentTimeMillis()).substring(0, 9));
        bill.setBillType(billType);
        bill.setEmpIdCreated(employeeId);
        bill.setCreated(new Date());
        bill.setEmpIdAuth("A1111");
        bill.setAuthDate(new Date());
        bill.setBillStatus(ConstStatus.BillStt.CREATE);
        return billRepository.save(bill);
    }
    public BillEntity updateBill(BillUpdateRequest updateRequest){
        BillEntity oldBill = billRepository.findById(Math.toIntExact(updateRequest.getBillId())).orElse(null);
        if (oldBill != null){
            for (BillDetailRequest ur: updateRequest.getBillDetails()){
                oldBill.getBillDetails().stream()
                        .forEach(bd -> {
                            if (bd.getProductId().equals(ur.getProductId())){
                                bd.setQuantity(bd.getQuantity()+ur.getQuantity());
                            } else {
                                billDetailService.add(oldBill,ur);
                            }
                        });
            }
        }
        return oldBill;
    }

    public BillEntity approvalBill(ApprovalRequest approvalRequest){
        BillEntity billApproval = billRepository.findById(approvalRequest.getBillId()).orElse(null);
        if (billApproval != null){
            billApproval.setBillStatus(approvalRequest.getBillStatus());
            return billRepository.save(billApproval);
        }
        return null;
    }

}
