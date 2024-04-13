package com.ra.web.api;


import com.ra.web.model.dto.AuthenticationRequest;
import com.ra.web.model.dto.RegisterRequest;
import com.ra.web.service.Impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class AuthenticationController {
    private final AuthenticationService authService;

    @GetMapping(value = {"","/home",})
    public String index(Model model){
        return "index";
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest registration,
            Model model
    ){
        String token = authService.register(registration);
        if (token != null){
            model.addAttribute("token",token);
        } else {
            model.addAttribute("fail","Đăng ký thất bại");

        }
        return index(model);
    }

    @PostMapping("/authenticate")
    public String authenticate(
        @RequestBody AuthenticationRequest authentication,
        Model model
    ){
        String token = authService.authenticate(authentication);
        if (token != null){
            model.addAttribute("token", token);
            model.addAttribute("success","Đăng nhập thành công");
        } else {
            model.addAttribute("fail","Đăng nhập thất bại");
        }
        return index(model);
    }
}
