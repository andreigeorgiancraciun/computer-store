package com.magazin.calculatoare.controllers.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return "home"; // This will resolve to the home.html template
    }

}

