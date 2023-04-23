package jbro.librarysystem.unit.book;

import jbro.librarysystem.book.Book;
import jbro.librarysystem.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    private Book dummyBook;

    @BeforeEach
    void setUp() throws IOException {
        dummyBook = new Book("Title 1", "Author 1", "ISBN 1", new MockMultipartFile("Image 1.jpg", "Mock Image 1".getBytes()).getBytes());
    }

    @Test
    void save() {
        //Given & When
        Long savedId = assertDoesNotThrow(() -> bookRepository.save(dummyBook));

        //Then
        assertNotNull(savedId);
    }

    @Test
    void findById() {
        //Given
        Long savedId = bookRepository.save(dummyBook);

        //When
        Book actualBook = bookRepository.findById(savedId);

        //Then
        assertThat(actualBook).isEqualTo(dummyBook);
    }
}
