package com.infy.webApp.service;

import java.util.List;

import com.infy.webApp.entity.Transaction;

import java.time.LocalDate;

import java.util.Map;

public interface RewardService {

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    Map<String, Object> calculateRewards(LocalDate startDate, LocalDate endDate);
}