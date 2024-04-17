package com.ra.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GGAOauth2Controller {

    @GetMapping("/user")
    public String oauth2(){
        return "";
    }

    @GetMapping()
    public Principal getUser(Principal principal){
        return principal;
    }
}
