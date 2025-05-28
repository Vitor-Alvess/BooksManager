package org.vitorAlves.infra.persistence;

import org.vitorAlves.shared.utils.StringListConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity (name = "books")
@Table (name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Convert(converter = StringListConverter.class)
    @Column(name = "authors", nullable = false)
    private List<String> authors;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    private String isbn;

    @Convert(converter = StringListConverter.class)
    @Column(name = "publishers", nullable = false)
    private List<String> publishers;

    @Convert(converter = StringListConverter.class)
    @Column(name = "similar_books")
    private List<String> similarBooks;
   

    public BookEntity() {
    }
    
    public BookEntity(String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks) {
        this.title = title;
        this.authors = authors;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.publishers = publishers;
        this.similarBooks = similarBooks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public List<String> getSimilarBooks() {
        return similarBooks;
    }

    public void setSimilarBooks(List<String> similarBooks) {
        this.similarBooks = similarBooks;
    }
}
