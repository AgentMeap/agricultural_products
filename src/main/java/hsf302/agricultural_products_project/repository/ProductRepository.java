package hsf302.agricultural_products_project.repository;

import hsf302.agricultural_products_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
