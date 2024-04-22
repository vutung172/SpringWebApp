package com.ra.web.service.Impl;

import com.ra.web.Enum.ConstStatus;
import com.ra.web.model.dto.request.ApprovalRequest;
import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.dto.request.BillUpdateRequest;
import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.BillDetailRepository;
import com.ra.web.repository.BillRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class BillServiceImpl {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailServiceImpl billDetailService;

    public BillEntity findById(long id) {
        BillEntity bill = billRepository.findById((int)id).orElse(null);
        if (bill != null){
            bill.setBillDetails(billDetailService.findByBillId(id));
            return bill;
        } else {return null;}

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
        BillEntity successBill = billRepository.save(bill);
        return billRepository.findById(Integer.parseInt(String.valueOf(successBill.getBillId()))).orElse(null);
    }
    public BillEntity updateBill(BillUpdateRequest updateRequest){
        BillEntity oldBill = billRepository.findById(Math.toIntExact(updateRequest.getBillId())).orElse(null);
        if (oldBill != null){
            for (BillDetailRequest ur: updateRequest.getBillDetails()){
                int count = 0;
                for (BillDetailsEntity bd : oldBill.getBillDetails()){
                    if (bd.getProductId().equals(ur.getProductId())){
                        bd.setQuantity(bd.getQuantity()+ur.getQuantity());
                        billDetailService.update(bd);
                        count++;
                    }
                }
                if (count == 0) {
                    billDetailService.add(oldBill, ur);
                }
            }
            return billRepository.findById(Integer.parseInt(String.valueOf(oldBill.getBillId()))).orElse(null);
        }
        return null;
    }

    public BillEntity approvalBill(ApprovalRequest approvalRequest){
        BillEntity billApproval = billRepository.findById(approvalRequest.getBillId()).orElse(null);
        if (billApproval != null){
            billApproval.setBillStatus(approvalRequest.getBillStatus());
            return billRepository.save(billApproval);
        }
        return null;
    }
    public BillEntity update(BillEntity bill){
        return billRepository.save(bill);
    }

}
