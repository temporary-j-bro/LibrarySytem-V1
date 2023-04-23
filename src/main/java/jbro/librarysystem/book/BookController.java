package jbro.librarysystem.book;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/management")
    public String getManagementRouter() {
        return "/books/managementRouter";
    }

    @GetMapping("/register-form")
    public String getRegisterForm(Model model) {
        model.addAttribute("registerForm", new BookRegisterForm());
        return "/books/registerForm";
    }

    @PostMapping(value = "/register-form", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String register(@ModelAttribute BookRegisterForm form, RedirectAttributes redirectAttributes) {
        bookRepository.save(form.toBook());

        redirectAttributes.addFlashAttribute("successMessage", "저장되었습니다");

        return "redirect:/books/register-form";
    }
}
