package hsf302.agricultural_products_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "Name", columnDefinition = "NVARCHAR(50)")
    @Nationalized
    private String name;

    @Column(name = "Description", columnDefinition = "NVARCHAR(500)")
    @Nationalized
    private String description;

    @OneToMany(mappedBy = "cate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
        product.setCate(this);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
        product.setCate(null);
    }
}
