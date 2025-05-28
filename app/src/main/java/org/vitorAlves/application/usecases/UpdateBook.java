package org.vitorAlves.application.usecases;

import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;

public class UpdateBook {
    private BooksDAO booksDAO;

    public UpdateBook(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    public Book execute(Book book) {
        return booksDAO.updateBook(book.getId(), book.getTitle(), book.getAuthors(), book.getPublishDate(), book.getIsbn(),
                book.getPublishers(), book.getSimilarBooks());
    }
}
