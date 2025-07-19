package hsf302.agricultural_products_project.controller;


import hsf302.agricultural_products_project.model.User;
import hsf302.agricultural_products_project.service.CategoryService;
import hsf302.agricultural_products_project.service.ProductService;
import hsf302.agricultural_products_project.service.UserService;
import hsf302.agricultural_products_project.utils.PasswordUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomePageController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("/index")
//    public String index(HttpSession session, Model model) {
//        User account = (User) session.getAttribute("account");
//        System.out.println("Session Account: " + account);
//        if (account != null) {
//            model.addAttribute("account", account);
//            System.out.println("Account: " + account.getUserName());
//            System.out.println(session.getAttribute("account"));
//            return "index";
//        }
//        return "index";
//    }

    @GetMapping("/about-us")
    public String aboutUs(HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("account", account);
        }
        return "about_us";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("account", account);
            return "profile";
        }
        return "redirect:/login";
    }

    @PostMapping("/update-profile")
    public String updateProfile(
            @RequestParam("userId") Long userId,
            @RequestParam("userFullName") String userFullName,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User currentUser = (User) session.getAttribute("account");

        if (currentUser != null && currentUser.getUserId().equals(userId)) {
            // Update user information
            currentUser.setUserFullName(userFullName);
            currentUser.setAddress(address);
            currentUser.setPhoneNumber(phoneNumber);

            // Save to database
            User updatedUser = userService.save(currentUser);

            // Update session
            session.setAttribute("account", updatedUser);

            redirectAttributes.addFlashAttribute("successMessage", "Thông tin cá nhân đã được cập nhật thành công!");
            return "redirect:/profile";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "Không thể cập nhật thông tin. Vui lòng thử lại!");
        return "redirect:/profile";
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("account", account);
            return "change-password";
        }
        return "redirect:/login";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User currentUser = (User) session.getAttribute("account");

        if (currentUser == null) {
            return "redirect:/login";
        }

        // Verify current password
        if (!PasswordUtils.verifyPassword(currentPassword, currentUser.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu hiện tại không đúng!");
            return "redirect:/change-password";
        }

        // Check if new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không khớp!");
            return "redirect:/change-password";
        }

        // Update password
        currentUser.setPassword(PasswordUtils.hashPassword(newPassword));
        User updatedUser = userService.save(currentUser);

        // Update session
        session.setAttribute("account", updatedUser);

        redirectAttributes.addFlashAttribute("successMessage", "Mật khẩu đã được thay đổi thành công!");
        return "redirect:/profile";
    }
}
