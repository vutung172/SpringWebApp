package com.ra.web.api;

import com.ra.web.model.entity.AccEntity;
import com.ra.web.model.entity.AccRoleEntity;
import com.ra.web.model.entity.RoleEntity;
import com.ra.web.repository.AccDetailsRepository;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.repository.RoleRepository;
import com.ra.web.service.Impl.AccDetailServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    private AccRepository accRepository;
    private RoleRepository roleRepository;
    private AccRoleRepository accRoleRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "/admin/dashboard";

    }

    @PostMapping("/{id}")
    public String setRole(@PathVariable int id, @RequestBody int roleId, Model model) {
        AccEntity acc = accRepository.findById(id).orElse(null);
        AccRoleEntity accRole = new AccRoleEntity();
        if (acc != null) {
            List<AccRoleEntity> accRoles = accRoleRepository.findAllByAccId(id);
            accRole.setAccId(id);
            accRole.setRoleId(roleId);
            accRoleRepository.deleteAll(accRoles);
            accRoleRepository.save(accRole);
            model.addAttribute("success", "Thêm role thành công");
        } else {
            model.addAttribute("fail", "Người dùng không tồn tại");
        }
        return dashboard(model);
    }
}
