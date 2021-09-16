package com.kimmy.ExpenseTrackerREStAPI.repository;

import com.kimmy.ExpenseTrackerREStAPI.entity.Category;
import com.kimmy.ExpenseTrackerREStAPI.entity.DAO.CategoryTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value="SELECT new com.kimmy.ExpenseTrackerREStAPI.entity.DAO.CategoryTotal (c.name, sum(COALESCE(p.price, 0)))" +
            " FROM Category c INNER  JOIN Product p " +
            "ON c.id = p.categoryId GROUP BY c.name ")
    List<CategoryTotal> getCategoriesAndTotalCost(int userId);
}
