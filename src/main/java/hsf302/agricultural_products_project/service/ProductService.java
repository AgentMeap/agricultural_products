package hsf302.agricultural_products_project.service;

import hsf302.agricultural_products_project.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
}
