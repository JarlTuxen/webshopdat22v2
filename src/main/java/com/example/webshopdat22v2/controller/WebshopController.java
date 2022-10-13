package com.example.webshopdat22v2.controller;

import com.example.webshopdat22v2.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebshopController {

    ProductRepository productRepository;

    public WebshopController(ProductRepository p){
        productRepository = p;
    }

    @GetMapping("/")
    public String index(Model model){
        productRepository.getAll();
        return "index";
    }

}
