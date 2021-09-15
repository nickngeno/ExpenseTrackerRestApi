package com.kimmy.ExpenseTrackerREStAPI.service;

import com.kimmy.ExpenseTrackerREStAPI.entity.Category;
import com.kimmy.ExpenseTrackerREStAPI.entity.Product;

import java.util.List;

public interface CategoryService {
    Category getCategory (int userId, int categoryId);
    List<Category> getCategories (int userId);
    Category addCategory (int userId, Category category );
    Category updateCategory (int userId, int id, Category category );
    void deleteCategory (int userId, int id);

}
