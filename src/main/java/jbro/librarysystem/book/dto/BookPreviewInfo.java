package jbro.librarysystem.book.dto;

import jbro.librarysystem.book.Book;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class BookPreviewInfo {

    private Long id;
    private String title;
    private String author;
    private byte[] image;

    public BookPreviewInfo(Long id, String title, String author, byte[] image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.image = image;
    }

    public static List<BookPreviewInfo> of(List<Book> books) {
        return books.stream()
                .map(book -> new BookPreviewInfo(book.getId(), book.getTitle(), book.getAuthor(), book.getImage()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBase64Image() {
        String base64Image = Base64.getEncoder().encodeToString(image);
        return "data:image/png;base64," + base64Image;
    }
}
