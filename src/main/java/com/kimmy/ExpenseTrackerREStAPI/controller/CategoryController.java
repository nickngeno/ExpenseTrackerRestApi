package com.kimmy.ExpenseTrackerREStAPI.controller;

import com.kimmy.ExpenseTrackerREStAPI.entity.Category;
import com.kimmy.ExpenseTrackerREStAPI.entity.Product;
import com.kimmy.ExpenseTrackerREStAPI.exception.ApiRequestException;
import com.kimmy.ExpenseTrackerREStAPI.repository.ProductRepository;
import com.kimmy.ExpenseTrackerREStAPI.service.CategoryService;
import com.kimmy.ExpenseTrackerREStAPI.service.serviceImpl.CategoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public Category addCategory( HttpServletRequest request, @RequestBody Category category) {
        int userId = (int) request.getAttribute("userId");
        return categoryService.addCategory(userId,category);
    }

    @GetMapping("/{id}")
    public Category getCategory(HttpServletRequest request, @PathVariable int id) {
        int userId = (int) request.getAttribute("userId");
        return categoryService.getCategory(userId,id);
    }

    @GetMapping("/all")
    public List<Category> fetchAll(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        return categoryService.getCategories(userId);
    }



    @PutMapping("/{id}")
    public Category updateCategory(HttpServletRequest request, @PathVariable int id, @RequestBody Category category) {
        int userId = (int) request.getAttribute("userId");
        return categoryService.updateCategory(id,userId,category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(HttpServletRequest request, @PathVariable int id) {
        int userId = (int) request.getAttribute("userId");
        categoryService.deleteCategory(userId, id);
    }
}
