package com.example.jbdl.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Book { // This class is mapped to a book table using an ORM framework (in this case Hibernate)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    /**
     * Current class   Other class (Opposite of current class)
     * ManyToOne --> OneToMany
     * OneToMany --> ManyToOne
     * OneToOne --> OneToOne
     * ManyToMany --> ManyToMany
     */

    // This is a foreign key column
    @ManyToOne
    @JoinColumn // Foreign key constraint
    @JsonIgnoreProperties(value = "bookList")
    private Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties(value = {"bookList", "transactionList"})
    private Student student;


    @OneToMany(mappedBy = "book") // back reference
    @JsonIgnoreProperties(value = {"book", "student"})
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

}
