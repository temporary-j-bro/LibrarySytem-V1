package jbro.librarysystem.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @GetMapping("/management")
    public String getManagementRouter() {
        return "/books/managementRouter";
    }

    @GetMapping("/register-form")
    public String getRegisterForm(Model model) {
        model.addAttribute("registerForm", new BookRegisterForm());
        return "/books/registerForm";
    }
}
