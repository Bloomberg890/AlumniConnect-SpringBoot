package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")    
    public String index() {
        return "home"; // Simple text response
    }
    @GetMapping("/footer")
    public String footer() {
        return "footer"; // Corresponds to about.html
    }
    @GetMapping("/mentorship")
    public String mentorship() {
        return "mentorship"; // Corresponds to about.html
    }
    @GetMapping("/messages")
    public String messages() {
        return "messages"; // Corresponds to about.html
    }
    @GetMapping("/navbar")
    public String navbar() {
        return "navbar"; // Corresponds to about.html
    }
    @GetMapping("/network")
    public String network() {
        return "network"; // Corresponds to about.html
    }
    @GetMapping("/profile")
    public String profile() {
        return "profile"; // Corresponds to about.html
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration"; // Corresponds to about.html
    }
    


}   
