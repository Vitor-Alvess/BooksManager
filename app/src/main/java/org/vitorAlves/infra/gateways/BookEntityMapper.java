package org.vitorAlves.infra.gateways;

import org.vitorAlves.domain.model.Book;
import org.vitorAlves.infra.persistence.BookEntity;

public class BookEntityMapper {
    public BookEntity toEntity(Book book) {
        return new BookEntity(
                book.getTitle(),
                book.getAuthors(),
                book.getPublishDate(),
                book.getIsbn(),
                book.getPublishers(),
                book.getSimilarBooks()
        );
    }

    public Book toDomain(BookEntity book) {
        return new Book(book.getId(),
                book.getTitle(),
                book.getAuthors(),
                book.getPublishDate(),
                book.getIsbn(),
                book.getPublishers(),
                book.getSimilarBooks()
        );
    }
}
