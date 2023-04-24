package jbro.librarysystem.book;

public class Book {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private byte[] image;

    public Book(String title, String author, String isbn, byte[] image) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.image = image;
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
}
