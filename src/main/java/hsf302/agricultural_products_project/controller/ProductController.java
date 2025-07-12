package hsf302.agricultural_products_project.controller;

import hsf302.agricultural_products_project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/product-detail")
    public String showProductDetail(HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("account", account);
        }
        return "product-detail"; // Trả về tên file .html trong /templates
    }
}
