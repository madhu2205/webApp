
package com.infy.webApp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.webApp.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);

	

}