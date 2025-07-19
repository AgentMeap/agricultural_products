package hsf302.agricultural_products_project.service;

import hsf302.agricultural_products_project.model.Product;
import hsf302.agricultural_products_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }
}
