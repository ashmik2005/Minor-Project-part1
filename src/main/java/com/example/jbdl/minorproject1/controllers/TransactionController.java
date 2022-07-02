package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.security.User;
import com.example.jbdl.minorproject1.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/issue")
    public String issueBook(@RequestParam("bookId") int bookId) throws Exception {
        // Student ID must not be accepted as an argument here. Here, any one
        // can issue a book on any other student's behalf. Then the fine will
        // be calculated on the wrong student. Thus we need authentication.
        // We must not accept the studentId as an argument, instead the studentId must be
        // retrieved from the bookId request (authentication context) itself. Our application needs security
        // book, student

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Now, from the user we have to get the student-Id
        Integer studentId = user.getStudent().getId();
        return transactionService.issueBook(bookId, studentId);

    }

    @PostMapping("/transaction/return")
    public String returnBook(@RequestParam("bookId") int bookId) throws Exception{

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer studentId = user.getStudent().getId();
        return transactionService.returnBook(bookId, studentId);
    }

    // TODO: API which allows to see student's transaction from last ____ time

}
