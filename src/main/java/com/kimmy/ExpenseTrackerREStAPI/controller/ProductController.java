package com.kimmy.ExpenseTrackerREStAPI.controller;

import com.kimmy.ExpenseTrackerREStAPI.entity.Product;
import com.kimmy.ExpenseTrackerREStAPI.service.CategoryService;
import com.kimmy.ExpenseTrackerREStAPI.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/products")
    public List<Product> getProducts(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        return productService.getProducts(userId);
    }

    @GetMapping("/products/{categoryId}")
    public List<Product> getAllProductsByCategory(HttpServletRequest request, @PathVariable int categoryId) {
        int userId = (int) request.getAttribute("userId");
        return productService.getAllProductsByCategory(userId,categoryId);
    }

    @PostMapping("/products")
    public Product addProduct(HttpServletRequest request, @RequestBody Product product) {
        int userId = (int) request.getAttribute("userId");
        return productService.addProduct(userId, product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(HttpServletRequest request, @PathVariable int id, @RequestBody Product product) {
        int userId = (int) request.getAttribute("userId");
        return productService.updateProduct(userId, id, product);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(HttpServletRequest request, @PathVariable int id) {
        int userId = (int) request.getAttribute("userId");
        productService.deleteProduct(userId, id);
    }
}
