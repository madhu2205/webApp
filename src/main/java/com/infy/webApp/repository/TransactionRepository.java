package com.infy.webApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.infy.webApp.entity.Transactions;
import java.util.*;

public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
	List<Transactions>findAll();

}
