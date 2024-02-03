package com.api.vendart.controllers;


import com.api.vendart.dtos.ProductDTO;
import com.api.vendart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<?> getAll() {
        return productService.getAll();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        return productService.show(id);
    }
    @PostMapping("/products/save")
    public ResponseEntity<?> save(ProductDTO dto) {
        return productService.save(dto);
    }
}
