package com.ra.web.api;

import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import com.ra.web.repository.AccRepository;
import com.ra.web.repository.AccRoleRepository;
import com.ra.web.repository.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
