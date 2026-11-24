package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

	@Entity
	@Table(name = "categories")
	public class Category {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "category_id")
	    private Integer categoryId;

	    @Column(name = "category_name", length = 100, nullable = false)
	    private String categoryName;

	    @Column(name = "description", length = 300)
	    private String description;

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    @Column(name = "status")
	    private Boolean status = true;

	    // Default Constructor
	    public Category() {
	    }

	    // Automatically set timestamps before inserting
	    @PrePersist
	    public void onCreate() {
	        createdAt = LocalDateTime.now();
	        updatedAt = LocalDateTime.now();
	    }

	    // Automatically update timestamp before updating
	    @PreUpdate
	    public void onUpdate() {
	        updatedAt = LocalDateTime.now();
	    }

	    // Getters and Setters

	    public Integer getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(Integer categoryId) {
	        this.categoryId = categoryId;
	    }

	    public String getCategoryName() {
	        return categoryName;
	    }

	    public void setCategoryName(String categoryName) {
	        this.categoryName = categoryName;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDateTime createdAt) {
	        this.createdAt = createdAt;
	    }

	    public LocalDateTime getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(LocalDateTime updatedAt) {
	        this.updatedAt = updatedAt;
	    }

	    public Boolean getStatus() {
	        return status;
	    }

	    public void setStatus(Boolean status) {
	        this.status = status;
	    }
	}
