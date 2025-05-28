package org.vitorAlves.infra.gateways;

import org.vitorAlves.application.gateways.BookImporter;
import org.vitorAlves.domain.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvBooksImporter implements BookImporter {
    private final String HEADER_LINE = "titulo,autores,publicacao,isbn,editoras,semelhantes";
    
    @Override
    public List<Book> importBooks(Path path) throws Exception {
        List<Book> books = new ArrayList<>();
        
        BufferedReader buffer = new BufferedReader(new FileReader(path.toFile()));

        String line;

        if (!buffer.readLine().equals(HEADER_LINE)) {
            throw new RuntimeException("Arquivo não contém o cabeçalho correto.");
        }

        while((line = buffer.readLine()) != null) {
            String[] fieldsValues = line.split(",");
            String title = fieldsValues[0].trim();
            List<String> authors = Arrays.stream(fieldsValues[1].split(";")).map(String::trim).collect(Collectors.toList());
            LocalDate publicationDate = LocalDate.parse(fieldsValues[2]);
            String isbn = fieldsValues[3].trim();
            List<String> publishers = Arrays.stream(fieldsValues[4].split(",")).map(String::trim).collect(Collectors.toList());
            List<String> similarBooks = Arrays.stream(fieldsValues[5].split(",")).map(String::trim).collect(Collectors.toList());

            books.add(new Book(title, authors, publicationDate, isbn, publishers, similarBooks));
        }
        return books;
    }
}
