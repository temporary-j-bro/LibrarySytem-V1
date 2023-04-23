package jbro.librarysystem.book;

import org.springframework.web.multipart.MultipartFile;

public class BookRegisterForm {
    private String title;
    private String author;
    private String isbn;
    private MultipartFile image;

    public BookRegisterForm() {
    }

    public BookRegisterForm(String title, String author, String isbn, MultipartFile image) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
