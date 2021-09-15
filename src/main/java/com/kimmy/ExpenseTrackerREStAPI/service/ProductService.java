package com.kimmy.ExpenseTrackerREStAPI.service;

import com.kimmy.ExpenseTrackerREStAPI.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProduct (int userId, int categoryId,int id);
    List<Product> getProducts (int userId);
    Product addProduct (int userId, Product product );
    Product updateProduct (int userId, int id, Product product );
    void deleteProduct (int userId, int id);
    List<Product> getAllProductsByCategory(int userId,int categoryId);
}
