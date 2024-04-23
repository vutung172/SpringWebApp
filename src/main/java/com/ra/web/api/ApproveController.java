package com.ra.web.api;

import com.ra.web.config.exception.BaseException;
import com.ra.web.model.dto.AccDto;
import com.ra.web.model.dto.BillDetailDto;
import com.ra.web.model.dto.BillDto;
import com.ra.web.model.dto.request.ApprovalRequest;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.model.entity.accounts.RoleEntity;
import com.ra.web.service.Impl.AccServiceImpl;
import com.ra.web.service.Impl.BillServiceImpl;
import com.ra.web.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity setRole(@PathVariable Integer accId, @RequestBody RoleEntity role) {
        AccEntity acc = accService.setRole(accId,role);
        AccDto accDto = mapper.convertEntityToDTO(acc, AccDto.class);
        return ResponseEntity.ok().body(accDto);
    }
    @PostMapping("/approval-import-bill/{employeeId}")
    public ResponseEntity approvalImportBill(
            @PathVariable String employeeId,
            @RequestBody ApprovalRequest approvalRequest
    ) {
        BillEntity bill = billService.approvalBill(approvalRequest);
        if (bill != null) {
            bill.setEmpIdAuth(employeeId);
            BillEntity updatedBill = billService.findById(bill.getBillId());
            BillDto billDto = mapper.convertEntityToDTO(updatedBill,BillDto.class);
            updatedBill.getBillDetails().forEach(bd -> billDto.getBillDetails().add(mapper.convertEntityToDTO(bd, BillDetailDto.class)));
            return ResponseEntity.ok(billDto);
        } else {
            throw new BaseException("RA-01-001");
        }
    }
}
