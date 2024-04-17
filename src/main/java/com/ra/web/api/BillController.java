package com.ra.web.api;

import com.ra.web.config.exception.BaseException;
import com.ra.web.model.dto.BillDto;
import com.ra.web.model.dto.request.ApprovalRequest;
import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.dto.request.BillUpdateRequest;
import com.ra.web.model.entity.BillDetailsEntity;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.service.Impl.BillDetailServiceImpl;
import com.ra.web.service.Impl.BillServiceImpl;
import com.ra.web.service.Impl.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bill")
@RequiredArgsConstructor
public class BillController {
    @Autowired
    private BillServiceImpl billService;
    @Autowired
    private BillDetailServiceImpl billDetailService;
    @Autowired
    private final Mapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity getBills(@PathVariable int id, Model model) {
        BillEntity bill = billService.findById(id);
        return ResponseEntity.ok().body(bill);
    }

    @PostMapping("/import-bill/{employeeId}")
    public ResponseEntity createBill(
            @PathVariable String employeeId,
            @RequestBody List<BillDetailRequest> billDetailRequests,
            Model model) {
        BillEntity newBill = billService.create(employeeId, true);
        if (newBill != null) {
            billDetailRequests.forEach(bdr -> billDetailService.add(newBill, bdr));
            BillEntity bill = billService.findById(newBill.getBillId());
            /*BillDto billDto = mapper.fromBillEntity(bill);*/
            return ResponseEntity.ok(bill);
        } else {
            throw new BaseException("RA-001-001");
        }
    }

    @PostMapping("/update-bill")
    public ResponseEntity updateBill(
            @RequestBody BillUpdateRequest updateRequests,
            Model model) {
        BillEntity newBill = billService.updateBill(updateRequests);
        if (newBill != null) {
            return ResponseEntity.ok(newBill);
        } else {
            throw new BaseException("RA-001-001");
        }
    }

    @PostMapping("/approval-import-bill/{employeeId}")
    public ResponseEntity approvalImportBill(
            @PathVariable String employeeId,
            @RequestBody ApprovalRequest approvalRequest,
            Model model
    ) {
        BillEntity bill = billService.approvalBill(approvalRequest);
        if (bill != null) {
            return ResponseEntity.ok(bill);
        } else {
            throw new BaseException("RA-001-001");
        }
    }
}

