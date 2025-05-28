/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.vitorAlves.application.usecases;

import org.vitorAlves.application.gateways.BooksDAO;
import org.vitorAlves.domain.model.Book;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author vitor-alves
 */
public class InsertBookDefault extends InsertBook{

    public InsertBookDefault(BooksDAO booksDAO) {
        super(booksDAO);
    }
    
    @Override
    public Book execute(String title, List<String> authors, LocalDate publishDate, String isbn, List<String> publishers, List<String> similarBooks) {
        return execute(new Book(title, authors, publishDate, isbn, publishers, similarBooks));
    }

    @Override
    public Book execute(String isbn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void executeImport(String path){
        throw new UnsupportedOperationException("Not supported"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
