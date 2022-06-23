package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.*;
import com.example.jbdl.minorproject1.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Value("${student.book.quota}")
    int studentBookQuota;

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepository transactionRepository;

    public String issueBook(int bookId, int studentId) throws Exception {

        /**
         * 1. Student is present or not
         * 2. Book is present or not and book is available
         * 3. Student's quota is exceeded or not
         * 4. Create a txn with PENDING status
         * 5. Make the book unavailable and assign it to a student
         * 6. Update the txn status to SUCCESS || Complete the transaction
         */

        // Student validations
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            throw new Exception("Student is not present, Unable to issue book");
        }

        if (student.getBookList().size() >= studentBookQuota) {
            throw new Exception("Student has exceeded their quota of books");
        }

        // Book validations
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            throw new Exception("Book does not exist, hence can't be issued");
        }

        if (book.getStudent() != null) {
            throw new Exception("Book already issued to someone");
        }

        // Creating transaction
        Transaction transaction = Transaction.builder()
                .book(book)
                .student(student)
                .transactionStatus(TransactionStatus.PENDING)
                .transactionType(TransactionType.ISSUE)
                .transactionId(UUID.randomUUID().toString())
                .build();

        // Save transaction with pending status
        transaction = transactionRepository.save(transaction);

        try{ // In this try-catch block Rolling back the transaction if it fails in between
            // Assign book to student and update book details (Make the book unavailable)
            book.setStudent(student);
            bookService.insert(book); // This going to update the existing book because book.id != null

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);

            transactionRepository.save(transaction);

        } catch(Exception e){
            book.setStudent(null);
            bookService.insert(book);

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
        }



        return transaction.getTransactionId();
    }

    public String returnBook(int bookId, int studentId) throws Exception{
        return null;
    }
}
