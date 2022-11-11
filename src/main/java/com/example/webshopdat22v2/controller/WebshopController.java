package com.example.webshopdat22v2.controller;

import com.example.webshopdat22v2.model.Product;
import com.example.webshopdat22v2.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class WebshopController {

    ProductRepository productRepository;

    public WebshopController(ProductRepository p){
        productRepository = p;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productRepository.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String showCreateProduct(){
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam("name") String navn, @RequestParam("price") int pris){

        Product newProduct = new Product();
        newProduct.setName(navn);
        newProduct.setPrice(pris);
        System.out.println(newProduct);
        //gem i repository
        productRepository.addProduct(newProduct);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateProduct(@PathVariable("id") int sletId, Model model){
        //hent produkt id fra repo og læg i model
        model.addAttribute("product",
                productRepository.findProductById(sletId));
        return "update";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product){
        productRepository.updateProduct(product);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int sletId){
        productRepository.deleteProductById(sletId);

        return "redirect:/";
    }

    @GetMapping("/cookieset")
    public String setCookie(HttpSession session){
        session.setAttribute("username", "Arne-Bjarne");
        return "CookieDemo";
    }

    @GetMapping("/cookieget")
    public String getCookie(HttpSession session){
        if (session.getAttribute("username") != null)         System.out.println(session.getAttribute("username"));
        else System.out.println("username ikke sat");
        return "CookieDemo";
    }

    @GetMapping("/cookieinvalidate")
    public String invalidateCookie(HttpSession session){
        session.invalidate();
        return "CookieDemo";
    }
}
