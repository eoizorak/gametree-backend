package com.gametree.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Procura um arquivo chamado index.html na pasta templates"
        return "index"; 
    }
}