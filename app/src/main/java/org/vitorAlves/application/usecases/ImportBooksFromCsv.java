package org.vitorAlves.application.usecases;

import org.vitorAlves.application.gateways.BookImporter;
import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class ImportBooksFromCsv extends InsertBook {
    private final BookImporter importer;

    public ImportBooksFromCsv(BooksDAO booksDAO, BookImporter importer) {
        super(booksDAO);
        this.importer = importer;
    }

    @Override
    public void executeImport(String path) throws Exception {
        execute(importer.importBooks(Paths.get(path)));
    }

    @Override
    public Book execute(String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks) {
        throw new UnsupportedOperationException("Not supported"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Book execute(String isbn) {
        throw new UnsupportedOperationException("Not supported"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
