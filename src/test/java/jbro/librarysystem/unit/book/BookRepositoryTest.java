package jbro.librarysystem.unit.book;

import jbro.librarysystem.book.Book;
import jbro.librarysystem.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    private Book dummyBook1;
    private Book dummyBook2;
    private Book dummyBook3;
    private Book dummyBook4;
    private Book dummyBook5;
    private Book dummyBook6;
    private Book dummyBook7;
    private Book dummyBook8;

    @BeforeEach
    void setUp() throws IOException {
        bookRepository.cleanUp();

        dummyBook1 = new Book("Title 1", "Author 1", "ISBN 1", new MockMultipartFile("Image 1.jpg", "Mock Image 1".getBytes()).getBytes());
        dummyBook2 = new Book("Title 2", "Author 2", "ISBN 2", new MockMultipartFile("Image 2.jpg", "Mock Image 2".getBytes()).getBytes());
        dummyBook3 = new Book("Title 3", "Author 3", "ISBN 3", new MockMultipartFile("Image 3.jpg", "Mock Image 3".getBytes()).getBytes());
        dummyBook4 = new Book("Title 4", "Author 4", "ISBN 4", new MockMultipartFile("Image 4.jpg", "Mock Image 4".getBytes()).getBytes());
        dummyBook5 = new Book("Title 5", "Author 5", "ISBN 5", new MockMultipartFile("Image 5.jpg", "Mock Image 5".getBytes()).getBytes());
        dummyBook6 = new Book("Special Title 1", "Author 1", "ISBN 6", new MockMultipartFile("Image 6.jpg", "Mock Image 6".getBytes()).getBytes());
        dummyBook7 = new Book("Special Title 2", "Author 1", "ISBN 7", new MockMultipartFile("Image 7.jpg", "Mock Image 7".getBytes()).getBytes());
        dummyBook8 = new Book("Special Title 3", "Author 2", "ISBN 8", new MockMultipartFile("Image 8.jpg", "Mock Image 8".getBytes()).getBytes());
    }

    @Test
    void save() {
        //Given & When
        Long savedId = assertDoesNotThrow(() -> bookRepository.save(dummyBook1));

        //Then
        assertNotNull(savedId);
    }

    @Test
    void findById() {
        //Given
        Long savedId = bookRepository.save(dummyBook1);

        //When
        Book actualBook = bookRepository.findById(savedId);

        //Then
        assertThat(actualBook).isEqualTo(dummyBook1);
    }

    @Test
    void findByKeyword() {
        //Given
        bookRepository.save(dummyBook1);
        bookRepository.save(dummyBook2);
        bookRepository.save(dummyBook3);
        bookRepository.save(dummyBook4);
        bookRepository.save(dummyBook5);
        bookRepository.save(dummyBook6);
        bookRepository.save(dummyBook7);
        bookRepository.save(dummyBook8);

        //When
        String keyword = "spe";
        List<Book> books = bookRepository.findByKeyword(keyword);

        //Then
        assertThat(books).containsOnly(dummyBook6, dummyBook7, dummyBook8);
    }
}
