package jbro.librarysystem.book;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
}
