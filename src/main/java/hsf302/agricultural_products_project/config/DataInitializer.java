package hsf302.agricultural_products_project.config;

import hsf302.agricultural_products_project.model.Category;
import hsf302.agricultural_products_project.model.Product;
import hsf302.agricultural_products_project.model.User; // Import the User model
import hsf302.agricultural_products_project.repository.CategoryRepository;
import hsf302.agricultural_products_project.repository.ProductRepository;
import hsf302.agricultural_products_project.repository.UserRepository; // Import the UserRepository
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.ParameterScriptAssertValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
// In a real application, you would inject PasswordEncoder
// import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 2. Update condition to check for users as well
        if (categoryRepository.count() == 0 && productRepository.count() == 0) {
            System.out.println("Bắt đầu khởi tạo dữ liệu mẫu... ⏳");
            createSampleData();
            System.out.println("Khởi tạo dữ liệu mẫu thành công! ✅");
        } else {
            System.out.println("Dữ liệu đã tồn tại, bỏ qua bước khởi tạo. ⏩");
        }
    }

    private void createSampleData() {
        // --- 1. Tạo các Danh mục (Categories) ---
        Category catRauCu = new Category();
        catRauCu.setName("Rau Củ Tươi");
        catRauCu.setDescription("Các loại rau củ quả tươi sạch, đạt chuẩn an toàn, được trồng theo phương pháp hữu cơ.");

        Category catTraiCay = new Category();
        catTraiCay.setName("Trái Cây Sạch");
        catTraiCay.setDescription("Trái cây đặc sản từ các vùng miền nổi tiếng của Việt Nam, đảm bảo độ tươi ngon.");

        Category catNguCoc = new Category();
        catNguCoc.setName("Ngũ Cốc Và Hạt");
        catNguCoc.setDescription("Các loại gạo, ngô, đậu và hạt dinh dưỡng chất lượng cao, tốt cho sức khỏe.");

        categoryRepository.saveAll(List.of(catRauCu, catTraiCay, catNguCoc));

        // --- 2. Tạo các Sản phẩm (Products) ---
        Product p1 = new Product(0L, "Cà Chua Đà Lạt", "Cà chua hữu cơ từ Đà Lạt, mọng nước, vị ngọt thanh, giàu vitamin A và C.", "/images/products/ca-chua-da-lat.jpg", 100, 35000.0, catRauCu);
        Product p2 = new Product(0L, "Bắp Cải Tím", "Bắp cải tím giòn, màu sắc bắt mắt, giàu chất chống oxy hóa, lý tưởng cho các món salad.", "/images/products/bap-cai-tim.jpg", 50, 25000.0, catRauCu);
        Product p3 = new Product(0L, "Khoai Lang Mật", "Khoai lang mật dẻo ngọt, thơm lừng khi nướng, là đặc sản nổi tiếng của vùng cao nguyên.", "/images/products/khoai-lang-mat.jpg", 150, 40000.0, catRauCu);
        Product p4 = new Product(0L, "Xoài Cát Hòa Lộc", "Xoài Cát Hòa Lộc chính gốc Tiền Giang, quả to, ngọt đậm, thơm lừng, hạt dẹt.", "/images/products/xoai-cat-hoa-loc.jpg", 80, 70000.0, catTraiCay);
        Product p5 = new Product(0L, "Sầu Riêng Ri Sáu", "Sầu riêng Ri Sáu cơm vàng hạt lép, béo ngậy, mùi thơm nồng nàn đặc trưng.", "/images/products/sau-rieng-ri6.jpg", 40, 180000.0, catTraiCay);
        Product p6 = new Product(0L, "Thanh Long Bình Thuận", "Thanh long ruột đỏ từ Bình Thuận, vị ngọt mát, nhiều nước và tốt cho hệ tiêu hóa.", "/images/products/thanh-long-binh-thuan.jpg", 200, 30000.0, catTraiCay);
        Product p7 = new Product(0L, "Gạo Thơm Sóc Trăng", "Gạo ngon nhất thế giới, hạt dài, trắng trong, cơm dẻo và thơm hương lá dứa tự nhiên.", "/images/products/gao-st25.jpg", 300, 195000.0, catNguCoc);
        Product p8 = new Product(0L, "Hạt Điều Bình Phước", "Hạt điều rang muối đặc sản Bình Phước, hạt to, giòn rụm, béo ngậy và đậm đà.", "/images/products/hat-dieu-binh-phuoc.jpg", 90, 250000.0, catNguCoc);

        productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8));

        // --- 3. Tạo người dùng (Users) ---
        // IMPORTANT: In a real application, you MUST hash the passwords!
        User admin = new User(
                "ad@a.com",
                "Quản Trị Viên",
                passwordEncoder.encode("ad"),
                "123 Đường Admin, Q.1, TP.HCM",
                true,
                "0901234567",
                "ROLE_ADMIN"
        );

        User member = new User(
                "mb@a.com",
                "Thành Viên A",
                passwordEncoder.encode("mb"),
                "456 Đường Member, Q.3, TP.HCM",
                true,
                "0987654321",
                "ROLE_MEMBER"
        );

        userRepository.saveAll(List.of(admin, member));
    }
}