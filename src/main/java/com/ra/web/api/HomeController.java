package com.ra.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/public")
public class HomeController {
    @GetMapping(value = {"","/","/index"})
    public String home(Model model){
        model.addAttribute("endpoint","hello world");
        return "index";
    }
}
