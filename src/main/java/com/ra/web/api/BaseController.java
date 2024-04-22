package com.ra.web.api;

import com.ra.web.model.entity.BillEntity;
import com.ra.web.service.Impl.BillDetailServiceImpl;
import com.ra.web.service.Impl.BillServiceImpl;
import com.ra.web.service.Impl.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/common")
@RequiredArgsConstructor
public class BaseController {
    @Autowired
    private BillServiceImpl billService;
    @Autowired
    private BillDetailServiceImpl billDetailService;
    @Autowired
    private final Mapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity getBills(@PathVariable int id) {
        BillEntity bill = billService.findById(id);
        return ResponseEntity.ok().body(bill);
    }
}

