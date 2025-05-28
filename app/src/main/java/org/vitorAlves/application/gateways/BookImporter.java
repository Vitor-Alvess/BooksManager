package org.vitorAlves.application.gateways;

import org.vitorAlves.domain.model.Book;

import java.nio.file.Path;
import java.util.List;

public interface BookImporter {
    List<Book> importBooks(Path path) throws Exception;
}
