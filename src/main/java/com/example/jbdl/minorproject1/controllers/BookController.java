package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.BookFilterKey;
import com.example.jbdl.minorproject1.models.Genre;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    // All the unsafe HTTP methods (POST, PUT, DELETE) must only be accessible to Admin
    // get --> Student, Admin

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        bookService.insert(bookCreateRequest.to());
    }

    // This API filters the books on basis of id
    @GetMapping("/book")
    public List<Book> getBook(@RequestParam("filterKey") String filterKey,
                        @RequestParam("filterValue") String filterValue) throws Exception {

        BookFilterKey bookFilterKey = BookFilterKey.valueOf(filterKey);


        switch (bookFilterKey){
            case AUTHOR_NAME:
                return bookService.getBookByAuthorName(filterValue);


            case BOOK_ID:
                return bookService.getBookById(Integer.parseInt(filterValue));


            case BOOK_NAME:
                return bookService.getBookByName(filterValue);


            case GENRE:
                return bookService.getBookByGenre(Genre.valueOf(filterValue));


            default:
                throw new Exception("Wrong filter type passed");
        }


    }

    @GetMapping("/book/all")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    /**
     * An API which would accept multiple filters
    **/

//    public List<Book> getBooks(@RequestParam("filterkeys") String filterKeys,
//                               @RequestParam("filterValues") String filterValues) {
//        // FilterKeys - BOOK_NAME, AUTHOR_NAME, COST
//        // FilterValues - Sapiens, Yuval Noah, 500
//        // FilterRegex -    =         s%     <=
//
//        return null;
//    }

}
