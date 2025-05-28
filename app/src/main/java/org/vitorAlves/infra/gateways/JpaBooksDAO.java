package org.vitorAlves.infra.gateways;

import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;
import org.vitorAlves.infra.persistence.BookEntity;
import org.vitorAlves.infra.persistence.PersistenceDAO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JpaBooksDAO implements BooksDAO {
    private final PersistenceDAO persistenceDAO = new PersistenceDAO();
    private final BookEntityMapper entityMapper = new BookEntityMapper();

    @Override
    public Book addBook(Book dto) {
        BookEntity entity = entityMapper.toEntity(dto);
        BookEntity entitySaved = persistenceDAO.save(entity);

        return entityMapper.toDomain(entitySaved);
    }

    @Override
    public void addBooks(List<Book> books) {
        books.forEach(book -> {
            Book alreadyOnDB = getBookByIsbn(book.getIsbn());
            
            if (alreadyOnDB == null) addBook(book);
            else
                updateBook(
                        alreadyOnDB.getId(),
                        book.getTitle(),
                        book.getAuthors(),
                        book.getPublishDate(),
                        book.getIsbn(),
                        book.getPublishers(),
                        book.getSimilarBooks()
                );
        });
    }
   

    @Override
    public Book updateBook(Long id, String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks) {
        BookEntity newEntity =  persistenceDAO.update(id, new BookEntity(title, authors, publishDate, isbn, publishers, similarBooks));
        return entityMapper.toDomain(newEntity);
    }

    @Override
    public Book deleteBook(Long id) {
        BookEntity toDelete = persistenceDAO.findById(id);
        BookEntity deleted = persistenceDAO.remove(toDelete);
        
        return entityMapper.toDomain(deleted);
    }

    @Override
    public List<Book> getAllBooks() {
        List<BookEntity> entityList = persistenceDAO.findAll();
        
        return entityList.stream().map(entityMapper::toDomain).collect(toList());
    }

    @Override
    public Book getBookById(Long id) {
        try {
            BookEntity entity = persistenceDAO.findById(id);
            return entityMapper.toDomain(entity);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        try {
            BookEntity entity = persistenceDAO.findByTitle(title);
            return entityMapper.toDomain(entity);
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Book> getBooksByAuthor(String author) { 
        try {
            List<BookEntity> entityList = persistenceDAO.findByAuthor(author);

            return entityList.stream().map(entityMapper::toDomain).collect(toList());
        }
        catch(Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> getBooksByPublishDate(LocalDate date) {
        try {
            List<BookEntity> entityList = persistenceDAO.findByPublishDate(date);

            return entityList.stream().map(entityMapper::toDomain).collect(toList());
        }
        catch(Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        try {
            BookEntity entity = persistenceDAO.findByIsbn(isbn);

            return entityMapper.toDomain(entity);
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        try {
            List<BookEntity> entityList = persistenceDAO.findByPublisher(publisher);

            return entityList.stream().map(entityMapper::toDomain).collect(toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> getBooksBySimilar(String similar) {
        try {
            List<BookEntity> entityList = persistenceDAO.findBySimilars(similar);

            return entityList.stream().map(entityMapper::toDomain).collect(toList());
        }
        catch(Exception e) {
            return Collections.emptyList();
        }
    }
}
