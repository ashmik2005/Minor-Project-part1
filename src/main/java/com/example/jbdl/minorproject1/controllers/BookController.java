package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        // TODO: We need to write the logic for author insertion
        bookService.insert(bookCreateRequest.to());
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam("id") int id){
        return bookService.getBookById(id);
    }

    @GetMapping("/book/all")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

}
