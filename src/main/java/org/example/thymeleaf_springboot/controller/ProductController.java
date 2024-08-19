package org.example.thymeleaf_springboot.controller;

import jakarta.validation.Valid;
import org.example.thymeleaf_springboot.model.Product;
import org.example.thymeleaf_springboot.model.ProductType;
import org.example.thymeleaf_springboot.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;//importer le package Model, qui permet de passer des données à la vue, et de les afficher
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller

public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        //on implemte String parceque on veut retourner le nom de la page html(thymeleaf)
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("types", ProductType.values());
        return "newProduct";
    }

    @PostMapping("/product")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        //BindingResult permet de verifier si il y a des erreurs de validation(formulaire html de thymeleaf)
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", ProductType.values());
            return "newProduct";
        }
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("types", ProductType.values());
        return "editProduct";
    }

    @PostMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", ProductType.values());
            return "editProduct";
        }
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/";
    }

}
