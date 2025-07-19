package hsf302.agricultural_products_project.controller;

import hsf302.agricultural_products_project.model.Category;
import hsf302.agricultural_products_project.model.Product;
import hsf302.agricultural_products_project.model.User;
import hsf302.agricultural_products_project.service.CategoryService;
import hsf302.agricultural_products_project.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/index"})
    public String showHomePage(HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("account", account);
        }

        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "index";
    }

    @GetMapping("/index1")
    public String showHomePage1(HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("account", account);
        }

        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "index1";
    }

    @GetMapping("/products/{id}")
    public String showProductDetail(@PathVariable("id") Long id, HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("account", account);
        }

        // --- Call the service method ---
        Optional<Product> productOptional = productService.getProductById(id);

        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "product-detail";
        } else {
            return "redirect:/index";
        }
    }
}
