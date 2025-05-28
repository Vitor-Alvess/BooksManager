package org.vitorAlves.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.vitorAlves.shared.utils.DateDeserializer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Book {
    private Long id;
    private String title;

    @JsonIgnore
    private List<String> authors;

    @JsonProperty("publish_date")
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    private String isbn;

    @JsonProperty("publishers")
    private List<String> publishers;

    private List<String> similarBooks;

    @JsonCreator
    public Book(@JsonProperty("title") String title,
                @JsonProperty("publish_date") LocalDate publishDate,
                @JsonProperty("publishers") String[] publishers) {
        this.title = title;
        this.publishDate = publishDate;
        this.publishers = Arrays.asList(publishers);
    }

    public Book(String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks) {
        if (title.equals("")) throw new IllegalArgumentException("Título inválido");
        if (authors.isEmpty()) authors.add("Desconhecido");
        if (isbn.equals("") || isbn.length() < 13) throw new IllegalArgumentException("Isbn inválido");
        if (publishers.isEmpty()) throw new IllegalArgumentException("Editoras não pode ser vazio");
        
        this.title = title;
        this.authors = authors;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.publishers = publishers;
        this.similarBooks = similarBooks;
    }

   
    public Book(Long id, String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.publishers = publishers;
        this.similarBooks = similarBooks;
    }

    public Object[] getData() {
        return new Object[]{
                id,
                title,
                String.join("; ", authors),
                publishDate.toString(),
                isbn,
                String.join("; ", publishers),
                String.join("; ", similarBooks)
        };
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        if (authors.isEmpty()) this.authors = Collections.singletonList("Desconhecido");
        else this.authors = authors;
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
