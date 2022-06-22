package com.example.jbdl.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50) // varchar(30)
    private String name;



    @Column(unique = true, nullable = false) // These are database validations
    private String email;

    // Back-reference to foreign key author in book table --> Bidirectional JPA relationship
    @OneToMany(mappedBy = "author") // mappedBy --> Tells which attribute this list of books is referring to
    @JsonIgnoreProperties(value = "author")
    private List<Book> bookList; // This will not be a column in the author table. This is just for java back reference

}
