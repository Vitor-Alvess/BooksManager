package org.vitorAlves.application.usecases;

import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;

import java.time.LocalDate;
import java.util.List;

public abstract class InsertBook {
    private final BooksDAO booksDAO;

    public InsertBook(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    public Book execute(Book book) {
        return booksDAO.addBook(book);
    }
    
    public void execute(List<Book> books) {
        booksDAO.addBooks(books);
    }
    
    public abstract void executeImport(String path) throws Exception;
    
    public abstract Book execute(String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks);
    
    public abstract Book execute(String isbn);
}
