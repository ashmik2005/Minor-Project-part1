package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Genre;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    // Book details
    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    // Author details
    @NotBlank
    private String authorName;

    @NotBlank
    @Email
    private String email;

    public Book to(){

        Author author = Author.builder()
                .name(this.authorName)
                .email(this.email)
                .build();

        return Book.builder()
                .name(this.name)
                .genre(this.genre)
                .author(author)
                .build();
    }

}
