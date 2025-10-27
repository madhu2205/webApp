
package com.infy.webApp.service;



import org.springframework.stereotype.Service;

import com.infy.webApp.entity.Transaction;
import com.infy.webApp.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

    private final TransactionRepository transactionRepository;

    public RewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private int calculatePoints(double amount) {
        if (amount > 100) {
            return (int) ((amount - 100) * 2 + 50);
        } else if (amount > 50) {
            return (int) (amount - 50);
        } else {
            return 0;
        }
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Map<String, Object> calculateRewards(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByDateBetween(startDate, endDate);

        // Monthly points
        Map<String, Map<Month, Integer>> monthlyPoints = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId,
                        Collectors.groupingBy(
                                t -> t.getDate().getMonth(),
                                Collectors.summingInt(t -> calculatePoints(t.getAmount()))
                        )));

        // Total points
        Map<String, Integer> totalPoints = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId,
                        Collectors.summingInt(t -> calculatePoints(t.getAmount()))));

        Map<String, Object> result = new HashMap<>();
        result.put("startDate", startDate);
        result.put("endDate", endDate);
        result.put("monthlyPoints", monthlyPoints);
        result.put("totalPoints", totalPoints);

        return result;
    }
}