package jbro.librarysystem.book;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    public static ConcurrentHashMap<Long, Book> repository = new ConcurrentHashMap<>();
    public static AtomicLong sequence = new AtomicLong();

    public void cleanUp() {
        repository.clear();
    }

    public Long save(Book book) {
        Long id = sequence.incrementAndGet();
        book.setId(id);

        repository.put(id, book);

        return id;
    }

    public Book findById(Long id) {
        return repository.get(id);
    }

    public List<Book> findByKeyword(String keyword) {
        return repository.values().stream()
                .filter(book -> book.contains(keyword))
                .collect(Collectors.toList());
    }
}
