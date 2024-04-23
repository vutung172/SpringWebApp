package com.ra.web.api;

import com.ra.web.config.exception.BaseException;
import com.ra.web.model.dto.BillDetailDto;
import com.ra.web.model.dto.BillDto;
import com.ra.web.model.dto.request.BillDetailRequest;
import com.ra.web.model.dto.request.BillUpdateRequest;
import com.ra.web.model.entity.BillEntity;
import com.ra.web.service.Impl.BillDetailServiceImpl;
import com.ra.web.service.Impl.BillServiceImpl;
import com.ra.web.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        BillEntity newBill = billService.create(employeeId,true);
        if (newBill != null) {
            billDetailRequests.forEach(bdr -> billDetailService.add(newBill, bdr));
            BillEntity bill = billService.findById(newBill.getBillId());
            BillDto billDto = mapper.convertEntityToDTO(bill,BillDto.class);
            bill.getBillDetails().forEach(bd -> billDto.getBillDetails().add(mapper.convertEntityToDTO(bd, BillDetailDto.class)));
            return ResponseEntity.ok(billDto);
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
                BillEntity updatedBill = billService.findById(newBill.getBillId());
                BillDto billDto = mapper.convertEntityToDTO(updatedBill,BillDto.class);
                updatedBill.getBillDetails().forEach(bd -> billDto.getBillDetails().add(mapper.convertEntityToDTO(bd, BillDetailDto.class)));
                return ResponseEntity.ok(billDto);
            } else {
                throw new BaseException("RA-01-002");
            }
        } else {
            throw new BaseException("RA-01-001");
        }
    }
}
