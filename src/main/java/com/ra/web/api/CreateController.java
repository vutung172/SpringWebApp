package com.ra.web.api;

import com.ra.web.config.exception.BaseException;
import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.dto.request.BillUpdateRequest;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.service.Impl.BillDetailServiceImpl;
import com.ra.web.service.Impl.BillServiceImpl;
import com.ra.web.service.Impl.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/create")
public class CreateController {
    @Autowired
    private BillServiceImpl billService;
    @Autowired
    private BillDetailServiceImpl billDetailService;
    @Autowired
    private Mapper mapper;

    @GetMapping(value = {"","/","/index"})
    public ResponseEntity home(){
        return ResponseEntity.ok().body("Create page");
    }

    @PostMapping("/import-bill/{employeeId}")
    public ResponseEntity createBill(
            @PathVariable String employeeId,
            @RequestBody List<BillDetailRequest> billDetailRequests) {
        BillEntity newBill = billService.create(employeeId, true);
        if (newBill != null) {
            billDetailRequests.forEach(bdr -> billDetailService.add(newBill, bdr));
            BillEntity bill = billService.findById(newBill.getBillId());
            /*BillDto billDto = mapper.fromBillEntity(bill);*/
            return ResponseEntity.ok(bill);
        } else {
            throw new BaseException("RA-01-001");
        }
    }

    @PostMapping("/update-bill/{employeeId}")
    public ResponseEntity updateBill(
            @PathVariable String employeeId,
            @RequestBody BillUpdateRequest updateRequests) {
        BillEntity bill = billService.findById(updateRequests.getBillId());
        if (bill != null) {
            if (bill.getEmpIdCreated().equals(employeeId)){
                BillEntity newBill = billService.updateBill(updateRequests);
                return ResponseEntity.ok(newBill);
            } else {
                throw new BaseException("RA-01-002");
            }
        } else {
            throw new BaseException("RA-01-001");
        }
    }
}
