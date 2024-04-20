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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AccServiceImpl accService;

    @GetMapping("/dashboard")
    public ResponseEntity dashboard() {
        return ResponseEntity.ok("dashboard");
    }

    @PostMapping("/set-role/{accId}")
    public ResponseEntity setRole(@PathVariable Integer accId, @RequestBody RoleEntity role, Model model) {
        AccEntity user = accService.setRole(accId,role);
        return ResponseEntity.ok().body(user);
    }
}
