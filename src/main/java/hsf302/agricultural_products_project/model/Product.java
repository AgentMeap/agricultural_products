package hsf302.agricultural_products_project.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Tên sản phẩm không được bỏ trống")
    @Nationalized
    @Column(name = "Name", nullable = false)
    @Size(min = 5, max = 50, message = "Độ dài tên sản phẩm phải từ 5 đến 50 ký tự")
    @Pattern(
            regexp = "^\\p{Lu}\\p{Ll}*(\\s\\p{L}\\p{Ll}*)*$",
            message = "Mỗi từ phải bắt đầu hoa, chỉ chứa chữ (Unicode), không số/ký tự đặc biệt, không khoảng trắng thừa"
    )
    private String productName;

    @NotNull(message = "Mô tả sản phẩm không được bỏ trống")
    @Nationalized
    @Column(name = "Description", nullable = false)
    @Size(min = 10, max = 500, message = "Độ dài mô tả sản phẩm phải từ 10 đến 500 ký tự")
    private String productDescription;

    @Column(name = "Image", nullable = false)
    @NotNull(message = "Hình ảnh sản phẩm không được để trống")
    private String productImage;

    @Column(name = "Quantity", nullable = false)
    @NotNull(message = "Số lượng sản phẩm không được để trống")
    private int productQuantity;

    @Column(name = "Price", nullable = false)
    @NotNull(message = "Giá sản phẩm không được để trống")

    private double productPrice;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category cate;


}
