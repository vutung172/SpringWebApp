package com.ra.web.service.Impl;

import com.ra.web.Enum.ConstStatus;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class BillServiceImpl {
    @Autowired
    private BillRepository billRepository;

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
    public BillEntity updateBill(BillEntity newBill){
        return billRepository.save(newBill);
    }

}
