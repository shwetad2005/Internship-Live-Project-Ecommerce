package com.ecommerce.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.repository.CategoryRepository;



@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create Category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // View All Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Update Category
    public Category updateCategory(Integer id, Category category) {

        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {

            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setDescription(category.getDescription());

            return categoryRepository.save(existingCategory);
        }

        return null;
    }

    // Deactivate Category
    public Category deactivateCategory(Integer id) {

        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            category.setStatus(false);
            return categoryRepository.save(category);
        }

        return null;
    }
}
