package org.vitorAlves.application.usecases;

import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;

import java.time.LocalDate;
import java.util.List;

public class ListBooks {
    private BooksDAO booksDAO;

    public ListBooks(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    public List<Book> listAllBooks() {
        return booksDAO.getAllBooks();
    }

    public Book getBookById(Long id) {
        return booksDAO.getBookById(id);
    }

    public Book getBookByTitle(String title) {
        return booksDAO.getBookByTitle(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return booksDAO.getBooksByAuthor(author);
    }

    public List<Book> getBooksByPublishDate(LocalDate date) {
        return booksDAO.getBooksByPublishDate(date);
    }

    public Book getBookByIsbn(String isbn) {
        return booksDAO.getBookByIsbn(isbn);
    }

    public List<Book> getBooksByPublisher(String publisher) {
        return booksDAO.getBooksByPublisher(publisher);
    }

    public List<Book> getBooksBySimilar(String similarBook) {
        return booksDAO.getBooksBySimilar(similarBook);
    }
}
