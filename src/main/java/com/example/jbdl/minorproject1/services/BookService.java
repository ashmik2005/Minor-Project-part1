package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Genre;
import com.example.jbdl.minorproject1.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public void insert(Book book){
        // TODO: We need to write the logic for author insertion. Author entity has to be inserted along with the book
        Author author = authorService.createOrGetAuthor(book.getAuthor());
        book.setAuthor(author);
        bookRepository.save(book);
    }

//    public Book getBookById(int id){
//        return bookRepository.findById(id).orElse(null);
//    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getBookByName(String name){
        return bookRepository.findByName(name);
    }

    public List<Book> getBookByGenre(Genre genre){
        return bookRepository.findByGenre(genre);
    }

    public List<Book> getBookByAuthorName(String name){
        return bookRepository.findByAuthorName(name);
    }

    public List<Book> getBookById(int id){

        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return Arrays.asList(bookOptional.get());
        }

        return new ArrayList<>();
    }




}
