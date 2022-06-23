package com.example.jbdl.minorproject1.repositories;

import com.example.jbdl.minorproject1.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
