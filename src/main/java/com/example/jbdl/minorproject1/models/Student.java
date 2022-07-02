package com.example.jbdl.minorproject1.models;

import com.example.jbdl.minorproject1.security.User;
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
public class Student {

    // Database level validations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties(value = {"student", "transactionList"})
    // mappedBy is always the current class' reference attribute in the other class
    private List<Book> bookList;

    @OneToOne
    @JoinColumn // In Student table, user_id is the foreign key
    @JsonIgnoreProperties("student")
    private User user;

    @OneToMany(mappedBy = "student") // back reference
    @JsonIgnoreProperties(value = {"student", "book"})
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;



}
