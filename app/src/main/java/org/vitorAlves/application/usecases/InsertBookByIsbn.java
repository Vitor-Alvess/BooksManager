package org.vitorAlves.application.usecases;

import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;
import org.vitorAlves.infra.webapi.BookWebAPI;

import java.time.LocalDate;
import java.util.List;

public class InsertBookByIsbn extends InsertBook {
    private final BookWebAPI api = new BookWebAPI();

    public InsertBookByIsbn(BooksDAO booksDAO) {
        super(booksDAO);
    }
    
    @Override
    public Book execute(String isbn) {
        return execute(api.fetchBookByIsbn(isbn));
    }

    @Override
    public Book execute(String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void executeImport(String path) {
        throw new UnsupportedOperationException("Not supported."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
