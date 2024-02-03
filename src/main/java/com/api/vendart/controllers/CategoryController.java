package com.api.vendart.controllers;

import com.api.vendart.dtos.CategoryDTO;
import com.api.vendart.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<?> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/categories/save")
    public ResponseEntity<?> save(@RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        return categoryService.show(id);
    }
}
