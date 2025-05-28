package org.vitorAlves.application.usecases;

import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;

public class DeleteBook {
    private BooksDAO booksDAO;

    public DeleteBook(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    public Book execute(Long id) {
        return booksDAO.deleteBook(id);
    }
}
