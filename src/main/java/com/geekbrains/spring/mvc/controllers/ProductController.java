package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// root: http://localhost:8989/app/customers

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // /add/customers
    @GetMapping
    public String showAll(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        // WEB-INF/templates/products.html
        return "products";
    }
//
//    @GetMapping("/product/{id}")
//    public String showProduct2(
//            @PathVariable Long id, Model model
//    ) {
//        model.addAttribute("product", productService.findById(id));
//        return "product";
//    }

    @GetMapping ("/main")
    public String showMain(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        // WEB-INF/templates/products.html
        return "main_page";
    }
    @GetMapping("/add")
    public String showAddForm() {
        return "product_add";
    }



    @PostMapping("/add")
    public String addProduct( @ModelAttribute Product newProduct) {
        productService.saveOrUpdate(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id, Model model
    ) {
        model.addAttribute("product", productService.findById(id));
        return "product_edit";
    }

    @PostMapping("/edit")
    public String modifyProduct( @ModelAttribute Product modifiedProduct) {
        productService.saveOrUpdate(modifiedProduct);
        return "redirect:/products/";
    }

    @GetMapping(value = "/json/{id}")
    public HttpEntity showJsonProduct( @PathVariable Long id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> entity = new HttpEntity<>(productService.findById(id), headers);
        return entity;
    }

    @GetMapping(value = "/product/{id}")
    public String showProduct(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }
}
