package com.kimmy.ExpenseTrackerREStAPI.repository;

import com.kimmy.ExpenseTrackerREStAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {
    List<Product>findByCategoryId(int categoryId);

    List<Product> findAllByUserId(int userId);

    List<Product> findAllByUserIdAndCategoryId(int userId, int categoryId);
}
