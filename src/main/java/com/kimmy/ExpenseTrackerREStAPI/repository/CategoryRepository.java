package com.kimmy.ExpenseTrackerREStAPI.repository;

import com.kimmy.ExpenseTrackerREStAPI.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
