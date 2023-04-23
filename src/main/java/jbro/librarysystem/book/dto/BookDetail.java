package jbro.librarysystem.book.dto;

import jbro.librarysystem.book.Book;

import java.util.Base64;

public class BookDetail {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private byte[] image;

    public BookDetail(Long id, String title, String author, String isbn, byte[] image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.image = image;
    }

    public static BookDetail of(Book book) {
        return new BookDetail(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getImage());
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
