package jbro.librarysystem.book;

import jbro.librarysystem.book.dto.BookDetail;
import jbro.librarysystem.book.dto.BookPreviewInfo;
import jbro.librarysystem.book.dto.BookRegisterForm;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public String register(@ModelAttribute BookRegisterForm form, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        Long savedId = bookRepository.save(form.toBook());

        response.setHeader("X-Saved-Id", String.valueOf(savedId));
        redirectAttributes.addFlashAttribute("successMessage", "저장되었습니다");

        return "redirect:/books/register-form";
    }

    @GetMapping("/{bookId}")
    public String getDetail(@PathVariable Long bookId, Model model) {
        BookDetail bookDetail = BookDetail.of(bookRepository.findById(bookId));
        model.addAttribute("bookDetail", bookDetail);

        return "/books/bookDetail";
    }

    @GetMapping
    public String getList(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size,
                          @RequestParam String keyword,
                          Model model
    ) {
        int offset = page * size;
        List<Book> books = bookRepository.findByKeyword(keyword, offset, size);

        List<BookPreviewInfo> bookPreviewInfos = BookPreviewInfo.of(books);
        model.addAttribute("bookPreviewInfos", bookPreviewInfos);

        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        int totalResults = bookRepository.countByKeyword(keyword);
        int totalPages = (int) Math.ceil((double) totalResults / size);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "/books/bookList";
    }
}
