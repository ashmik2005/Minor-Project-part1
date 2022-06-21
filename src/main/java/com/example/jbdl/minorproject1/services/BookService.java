package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public void insert(Book book){
        bookRepository.save(book);
    }

    public Book getBookById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


}
