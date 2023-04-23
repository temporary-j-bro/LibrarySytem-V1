package jbro.librarysystem.unit.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}
