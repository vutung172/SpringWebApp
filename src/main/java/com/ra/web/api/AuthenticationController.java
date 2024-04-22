package com.ra.web.api;

import com.ra.web.model.dto.TokenDto;
import com.ra.web.model.dto.request.AuthenticationRequest;
import com.ra.web.model.dto.request.RegisterRequest;
import com.ra.web.model.entity.TokenEntity;
import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.service.Impl.AccServiceImpl;
import com.ra.web.service.Impl.AuthenticationService;
import com.ra.web.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private AccServiceImpl accService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = {"","/home",})
    public ResponseEntity index(Model model){
        model.addAttribute(accService.findAll());
        return ResponseEntity.ok(model);
    }

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest registration
    ){
        String token = authService.register(registration);
        if (token != null){
            return ResponseEntity.ok().body(token);
        } else {
            return  ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(
        @RequestBody AuthenticationRequest authentication
    ){
        TokenEntity token = new TokenEntity(authService.authenticate(authentication));
        if (token.getToken() != null){
            String userName = jwtUtil.getUserName(token.getToken());
            String employeeId = accService.findEmpIdByUserName(userName);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setEmployeeId(employeeId);
            return ResponseEntity.ok().body(tokenDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("log-out")
    public ResponseEntity logout(){
        return ResponseEntity.ok().build();
    }

}
