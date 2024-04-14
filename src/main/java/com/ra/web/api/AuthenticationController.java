package com.ra.web.api;

import com.ra.web.model.dto.AuthenticationRequest;
import com.ra.web.model.dto.RegisterRequest;
import com.ra.web.model.entity.TokenEntity;
import com.ra.web.service.Impl.AccServiceImpl;
import com.ra.web.service.Impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class AuthenticationController {
    private final AuthenticationService authService;
    private final AccServiceImpl accService;

    @GetMapping(value = {"","/home",})
    public ResponseEntity index(Model model){
        model.addAttribute(accService.findAll());
        return ResponseEntity.ok(model);
    }

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest registration,
            Model model
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
        @RequestBody AuthenticationRequest authentication,
        Model model
    ){
        TokenEntity token = new TokenEntity(authService.authenticate(authentication));
        if (token.getToken() != null){
            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("log-out")
    public ResponseEntity logout(){
        return ResponseEntity.ok().build();
    }
}
