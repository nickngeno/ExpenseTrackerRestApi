package com.kimmy.ExpenseTrackerREStAPI.service.serviceImpl;

import com.kimmy.ExpenseTrackerREStAPI.entity.Product;
import com.kimmy.ExpenseTrackerREStAPI.exception.ApiRequestException;
import com.kimmy.ExpenseTrackerREStAPI.repository.ProductRepository;
import com.kimmy.ExpenseTrackerREStAPI.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getProduct(int userId, int categoryId, int id) {
        return null;
    }

    @Override
    public List<Product> getProducts(int userId) {
        return productRepository.findAllByUserId(userId);
    }

    @Override
    public List<Product> getAllProductsByCategory(int userId,int categoryId) {
        return productRepository.findAllByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    public Product addProduct(int userId, Product product) {
        Product myProduct = new Product();
        myProduct.setName(product.getName());
        myProduct.setPrice(product.getPrice());
        myProduct.setCategoryId(product.getCategoryId());
        myProduct.setUserId(userId);

        return productRepository.save(myProduct);
    }

    @Override
    public Product updateProduct(int userId, int id, Product product) {

        Product product1 = productRepository.findById(id).orElseThrow(() -> new ApiRequestException("No such element found"));
        if (product1 != null) {
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setUserId(userId);
            product1.setCategoryId(product.getCategoryId());

            productRepository.save(product1);
        }
        return product1;
    }

    @Override
    public void deleteProduct(int userId, int id) {
        Product product1 = productRepository.findById(id).orElseThrow(() -> new ApiRequestException("No such element found"));
        if (product1 != null) {
            productRepository.delete(product1);
        }
    }
}
