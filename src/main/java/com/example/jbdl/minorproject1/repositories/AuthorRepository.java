package com.example.jbdl.minorproject1.repositories;
import com.example.jbdl.minorproject1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    /** Custom queries
     * 1. JPQL : Java Persistence Query Language (format which executes query considering java classes / objects)
     * 2. Native query: Format which executes query considering sql tables / relations


     * By default, Hibernate assumes every query as a JPQ
     * :<arg_name> --> refer by the arg name
     * ?<arg_number> --> refer by the arg number
     */
    @Query(value = "select a from Author a where a.email = :email") // Custom query. The ORM is done by hibernate
    Author findAuthor(String email);
//
//    @Query(value = "select a from Author a where a.name = :name and a.email = :email") // JPQL --> write query here w.r.t java classes
//    Author findAuthorByNameAndEmail(String name, String email);
//
//    @Query(value = "select * from author where author_id = ?1", nativeQuery = true) // Native Query --> write query here w.r.t tables in our db
//    Author findAuthorById2(int id);

}
