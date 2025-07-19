package hsf302.agricultural_products_project.service;

import hsf302.agricultural_products_project.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
}
