package com.kimmy.ExpenseTrackerREStAPI.service.serviceImpl;

import com.kimmy.ExpenseTrackerREStAPI.entity.Category;
import com.kimmy.ExpenseTrackerREStAPI.entity.DAO.CategoryTotal;
import com.kimmy.ExpenseTrackerREStAPI.entity.Product;
import com.kimmy.ExpenseTrackerREStAPI.exception.ApiRequestException;
import com.kimmy.ExpenseTrackerREStAPI.repository.CategoryRepository;
import com.kimmy.ExpenseTrackerREStAPI.repository.ProductRepository;
import com.kimmy.ExpenseTrackerREStAPI.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category getCategory(int userId, int categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ApiRequestException("No such category found"));
    }

    @Override
    public ResponseEntity<List<CategoryTotal>> getCategoriesAndTotalCost(int userId) {
        List<CategoryTotal > categories =  categoryRepository.getCategoriesAndTotalCost(userId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Override
    public List<Category> getCategories(int userId) {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(int userId, Category category) {
        category.setUserId(userId);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int userId, int id, Category category) {
        Category existingCategory = getCategory(userId, id);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            existingCategory.setUserId(userId);
            categoryRepository.save(existingCategory);
        } else throw new ApiRequestException("No such element exists!");
        return existingCategory;
    }

    @Override
    public void deleteCategory(int userId, int categoryId) {
        Category categoryExists = getCategory(userId, categoryId);
        if (categoryExists != null) {
            List<Product> products = productRepository.findByCategoryId(categoryId);
            for(Product product : products){
                productRepository.delete(product);
            }
            categoryRepository.delete(categoryExists);
        }
    }

}