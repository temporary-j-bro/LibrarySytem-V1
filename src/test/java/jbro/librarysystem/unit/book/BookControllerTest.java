package jbro.librarysystem.unit.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private MockMultipartFile mockMultipartFile;

    @BeforeEach
    void setUp() {
        mockMultipartFile = new MockMultipartFile("image", "Image 1.jpg", "image/jpg", "Mock Image 1".getBytes());
    }

    @Test
    public void bookManagementRouter() throws Exception {
        mockMvc.perform(get("/books/management"))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/managementRouter"));
    }

    @Test
    public void bookRegisterForm() throws Exception {
        mockMvc.perform(get("/books/register-form"))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/registerForm"));
    }

    @Test
    public void registerBook() throws Exception {
        mockMvc.perform(multipart("/books/register-form")
                        .file(mockMultipartFile)
                        .param("title", "Title 1")
                        .param("author", "Author 1")
                        .param("isbn", "ISBN 1"))
                .andExpect(status().isFound())
                .andExpect(header().exists("X-Saved-Id"))
                .andExpect(view().name("redirect:/books/register-form"));
    }

    @Test
    public void getBookDetail() throws Exception {
        //Given
        String savedId = mockMvc.perform(multipart("/books/register-form")
                        .file(mockMultipartFile)
                        .param("title", "Title 1")
                        .param("author", "Author 1")
                        .param("isbn", "ISBN 1"))
                .andReturn()
                .getResponse()
                .getHeader("X-Saved-Id");

        //When & Then
        mockMvc.perform(get("/books/{bookId}", savedId))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/bookDetail"));
    }
}
