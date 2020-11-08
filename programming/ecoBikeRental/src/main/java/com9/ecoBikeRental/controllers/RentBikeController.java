package com9.ecoBikeRental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentBikeController {

    @GetMapping(value="/")
    public String index(Model model){
        return "index";
    }
}
