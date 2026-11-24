package com.ecommerce.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.Service.CategoryService;
import com.ecommerce.backend.entity.Category;


@RestController
@RequestMapping("/api/categories")
@CrossOrigin (origins="")       //(origins = "http://localhost:5173")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create Category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    // View All Categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Update Category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Integer id,
            @RequestBody Category category) {

        Category updatedCategory = categoryService.updateCategory(id, category);

        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedCategory);
    }

    // Deactivate Category
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Category> deactivateCategory(@PathVariable Integer id) {

        Category deactivatedCategory = categoryService.deactivateCategory(id);

        if (deactivatedCategory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deactivatedCategory);
    }
}