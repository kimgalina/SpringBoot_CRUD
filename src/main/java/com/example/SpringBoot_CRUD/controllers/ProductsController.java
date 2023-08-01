package com.example.SpringBoot_CRUD.controllers;

import com.example.SpringBoot_CRUD.DAO.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductDAO  productDAO;

    @GetMapping("/")
    public String showProducts(Model model){

        model.addAttribute("products",productDAO.getAllProducts());
        return "Products/ShowAll";
    }
}
