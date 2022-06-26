package com.example.jbdl.minorproject1.repositories;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
//    @Query("select b from book b where b.author.name = ?1")
    List<Book> findByAuthorName(String authorName);

    List<Book> findByName(String name);

    List<Book> findByGenre(Genre genre);

}
