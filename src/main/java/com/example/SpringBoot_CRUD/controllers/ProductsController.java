package com.example.SpringBoot_CRUD.controllers;

import com.example.SpringBoot_CRUD.DAO.ProductDAO;
import com.example.SpringBoot_CRUD.models.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public String productInfo(@PathVariable("id") int id,Model model){
        model.addAttribute("product",productDAO.findById(id));
        return "Products/productInfo";
    }

    @GetMapping("/new")
    public String addProduct(Model model){
        model.addAttribute("product",new Product());
        return "Products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult){
        // checking for successful validation
        if(bindingResult.hasErrors()){
            //if there are some errors return to a page with the form
            return "Products/new";
        }
        //if no any errors add new product to DB
        productDAO.addProduct(product);

        return "redirect:/products/";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id , Model model){
        // ложим в модель человека именно с этим id
        model.addAttribute("product",productDAO.findById(id));
        return "Products/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            return "Products/edit";
        }
        productDAO.update(product,id);
        return "redirect:/products/";

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
       productDAO.delete(id);
        return "redirect:/products/";
    }



}
