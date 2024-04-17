package com.ra.web.api;

import com.ra.web.model.dto.UserDto;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import com.ra.web.model.entity.accounts.RoleEntity;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.repository.RoleRepository;
import com.ra.web.service.AccService;
import com.ra.web.service.Impl.AccServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AccServiceImpl accService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "/admin/dashboard";
    }

   /* @PostMapping("/{id}")
    public ResponseEntity setRole(@RequestBody AccEntity acc, @RequestBody RoleEntity role, Model model) {
        UserDto user = accService.setRole(acc,role);
        return ResponseEntity.ok().body(accSetNewRole);
    }*/
}
