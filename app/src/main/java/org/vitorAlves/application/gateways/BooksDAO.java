package org.vitorAlves.application.gateways;

import org.vitorAlves.domain.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BooksDAO {
    Book addBook(Book book);
    void addBooks(List<Book> books);
    
    Book updateBook(Long id, String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publisher, List<String> similarBooks);
    Book deleteBook(Long id);

    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book getBookByTitle(String title);
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByPublishDate(LocalDate date);
    Book getBookByIsbn(String isbn);
    List<Book> getBooksByPublisher(String publisher);
    List<Book> getBooksBySimilar(String similarBook);
}
