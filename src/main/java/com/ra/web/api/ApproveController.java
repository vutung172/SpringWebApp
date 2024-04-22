package com.ra.web.api;

import com.ra.web.config.exception.BaseException;
import com.ra.web.model.dto.request.ApprovalRequest;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.model.entity.accounts.RoleEntity;
import com.ra.web.service.Impl.AccServiceImpl;
import com.ra.web.service.Impl.BillServiceImpl;
import com.ra.web.service.Impl.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class ApproveController {
    @Autowired
    private AccServiceImpl accService;
    @Autowired
    private BillServiceImpl billService;
    @Autowired
    private Mapper mapper;

    @GetMapping("/dashboard")
    public ResponseEntity dashboard() {
        return ResponseEntity.ok("dashboard");
    }

    @PostMapping("/set-role/{accId}")
    public ResponseEntity setRole(@PathVariable Integer accId, @RequestBody RoleEntity role, Model model) {
        AccEntity user = accService.setRole(accId,role);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/approval-import-bill/{employeeId}")
    public ResponseEntity approvalImportBill(
            @PathVariable String employeeId,
            @RequestBody ApprovalRequest approvalRequest
    ) {
        BillEntity bill = billService.approvalBill(approvalRequest);
        if (bill != null) {
            bill.setEmpIdAuth(employeeId);
            billService.update(bill);
            return ResponseEntity.ok(bill);
        } else {
            throw new BaseException("RA-001-001");
        }
    }
}
