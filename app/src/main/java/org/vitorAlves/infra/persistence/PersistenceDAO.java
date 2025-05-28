package org.vitorAlves.infra.persistence;

import org.vitorAlves.config.HibernateConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class PersistenceDAO {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public PersistenceDAO() {
        this.entityManagerFactory = HibernateConfig.getEntityManagerFactory();
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public BookEntity save(BookEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        return entity;
    };

    public BookEntity update(Long id, BookEntity newBook) {
        if (newBook == null) throw new IllegalArgumentException("Entity cannot be null");
        if (id == null) throw new IllegalArgumentException("Isbn cannot be null");

        BookEntity found = findById(id);
        if (found == null) throw new IllegalArgumentException("Entity not found");
        
        newBook.setId(found.getId());
        
        entityManager.getTransaction().begin();
        entityManager.merge(newBook);
        entityManager.getTransaction().commit();
        
        return newBook;
    }

    public BookEntity remove(BookEntity toBeDeleted) {
        if (toBeDeleted == null) throw new IllegalArgumentException("Entity not found");
        
        entityManager.getTransaction().begin();
        entityManager.remove(toBeDeleted);
        entityManager.getTransaction().commit();
        
        return toBeDeleted;
    }

    public List<BookEntity> findAll() {
        entityManager.clear();
        entityManager.getTransaction().begin();
        List<BookEntity> list = entityManager.createQuery("from books order by id", BookEntity.class).getResultList();
        entityManager.getTransaction().commit();

        return list;
    }

    public BookEntity findById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");

        try {
            entityManager.clear();
            entityManager.getTransaction().begin();
            BookEntity entity = entityManager.find(BookEntity.class, id);
            entityManager.getTransaction().commit();
            return entity;
        }
        catch (EntityNotFoundException e) {
            entityManager.getTransaction().rollback();
            throw new EntityNotFoundException("Book not found");
        }
    }

    public BookEntity findByTitle(String title){
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        
        try {
            entityManager.clear();
            entityManager.getTransaction().begin();

            BookEntity entity = (BookEntity) entityManager
                    .createNativeQuery("SELECT * FROM books WHERE title ILIKE :title ORDER BY id", BookEntity.class)
                    .setParameter("title", title)
                    .getSingleResult(); 

            entityManager.getTransaction().commit();
            return entity;
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            throw new EntityNotFoundException("Book with title '" + title + "' not found");
        }

    }

    public List<BookEntity> findByAuthor(String author){
        if (author == null) throw new IllegalArgumentException("Author cannot be null");
            
        author = format(author);
        
        entityManager.clear();
        entityManager.getTransaction().begin();

        List<BookEntity> entityList = entityManager
                .createNativeQuery("SELECT * FROM books WHERE authors ILIKE :author ORDER BY id", BookEntity.class)
                .setParameter("author", author)
                .getResultList();

        entityManager.getTransaction().commit();
        return entityList;
        
    }

    public List<BookEntity> findByPublishDate(LocalDate date){
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        
        entityManager.clear();
        entityManager.getTransaction().begin();

        List<BookEntity> list = entityManager
                .createQuery("from books where publishDate between :date and :datePlusOne ordey by id", BookEntity.class)
                .setParameter("date", date)
                .setParameter("datePlusOne", date.plusDays(1))
                .getResultList();

        entityManager.getTransaction().commit();
        return list;
    }

    public BookEntity findByIsbn(String isbn) {
        if (isbn == null) throw new IllegalArgumentException("ISBN cannot be null");

        try {
            entityManager.clear();    
            entityManager.getTransaction().begin();

            BookEntity entity = entityManager
                    .createQuery("from books where isbn like :isbn", BookEntity.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            return entity;
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            throw new EntityNotFoundException("Book with ISBN '" + isbn + "' not found");
        }
    }

    public List<BookEntity> findByPublisher(String publisher){
        if (publisher == null) throw new IllegalArgumentException("Publisher cannot be null");

        publisher = format(publisher);
        
        entityManager.clear();    
        entityManager.getTransaction().begin();

        List<BookEntity> list = entityManager
                .createNativeQuery("SELECT * FROM books WHERE publishers ILIKE :publisher ORDER BY id", BookEntity.class)
                .setParameter("publisher", publisher)
                .getResultList();

        entityManager.getTransaction().commit();
        return list;
    }

    public List<BookEntity> findBySimilars(String similar) {
        if(similar == null) throw new IllegalArgumentException("Similar cannot be null");
            
        similar = format(similar);
        
        entityManager.clear();    
        entityManager.getTransaction().begin();

        List<BookEntity> list = entityManager
                .createNativeQuery("SELECT * FROM books WHERE similar_books ILIKE :similar ORDER BY id", BookEntity.class)
                .setParameter("similar", similar)
                .getResultList();

        entityManager.getTransaction().commit();
        
        return list;
    }
    
    private String format(String str) {
        StringBuilder formatted = new StringBuilder();
        formatted.append("%").append(str).append("%;");
        
        return formatted.toString();
    }
}
