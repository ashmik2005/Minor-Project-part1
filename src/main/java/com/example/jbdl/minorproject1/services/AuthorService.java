package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    // This function is responsible for fetching the author with given email
    // and if it doesn't exist then it will create the author and return it.
    public Author createOrGetAuthor(Author author){
        Author authorFromDB =  authorRepository.findAuthor(author.getEmail());

        if (authorFromDB == null){
            authorFromDB = authorRepository.save(author);
        }

        return authorFromDB;
    }

}
