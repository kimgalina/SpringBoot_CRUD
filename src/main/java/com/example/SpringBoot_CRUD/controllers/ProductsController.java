package com.example.SpringBoot_CRUD.controllers;

import com.example.SpringBoot_CRUD.DAO.ProductDAO;
import com.example.SpringBoot_CRUD.models.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;

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

    @GetMapping("/new")
    public String addProduct(Model model){
        model.addAttribute("product",new Product());
        return "/Products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult){
        // checking for successful validation
        if(bindingResult.hasErrors()){
            //if there are some errors return to a page with the form
            return "/Products/new";
        }
        //if no any errors add new product to DB
        productDAO.addProduct(product);

        return "redirect:/products/";
    }


}
