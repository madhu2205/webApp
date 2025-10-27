package com.infy.webApp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infy.webApp.entity.Transaction;
import com.infy.webApp.repository.TransactionRepository;
import com.infy.webApp.service.RewardServiceImpl;

public class WebAppApplicationTests {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        transactions = new ArrayList<>();
        transactions.add(createTransaction("1", 120.0, LocalDate.of(2025, 10, 5)));  // 90 points
        transactions.add(createTransaction("1", 75.0, LocalDate.of(2025, 10, 10)));  // 25 points
        transactions.add(createTransaction("2", 40.0, LocalDate.of(2025, 10, 15)));  // 0 points
        transactions.add(createTransaction("1", 150.0, LocalDate.of(2025, 11, 5)));  // 150: 50+(50*2)=150 points
    }

    private Transaction createTransaction(String customerId, Double amount, LocalDate date) {
        Transaction t = new Transaction();
        t.setCustomerId(customerId);
        t.setAmount(amount);
        t.setDate(date);
        return t;
    }

    @Test
    void testCalculateRewards_ReturnsMonthlyAndTotalPoints() {
        LocalDate start = LocalDate.of(2025, 10, 1);
        LocalDate end = LocalDate.of(2025, 11, 30);

        when(transactionRepository.findByDateBetween(start, end)).thenReturn(transactions);

        Map<String, Object> result = rewardService.calculateRewards(start, end);

        assertNotNull(result);
        assertTrue(result.containsKey("monthlyPoints"));
        assertTrue(result.containsKey("totalPoints"));

        // Extract maps safely
        @SuppressWarnings("unchecked")
        Map<Long, Map<Integer, Integer>> monthlyPoints =
                (Map<Long, Map<Integer, Integer>>) result.get("monthlyPoints");

        @SuppressWarnings("unchecked")
        Map<Long, Integer> totalPoints =
                (Map<Long, Integer>) result.get("totalPoints");

        // ✅ Monthly points checks
        assertEquals(2, monthlyPoints.size());
        assertEquals(115, monthlyPoints.get(1L).get(10)); // Oct (90+25)
        assertEquals(150, monthlyPoints.get(1L).get(11)); // Nov
        assertEquals(0, monthlyPoints.get(2L).get(10));   // Customer 2, Oct

        // ✅ Total points checks
        assertEquals(265, totalPoints.get(1L)); // 115 + 150
        assertEquals(0, totalPoints.get(2L));

        verify(transactionRepository, times(1)).findByDateBetween(start, end);
    }

    @Test
    void testCalculateRewards_WhenNoTransactions() {
        when(transactionRepository.findByDateBetween(any(), any())).thenReturn(Collections.emptyList());

        Map<String, Object> result = rewardService.calculateRewards(LocalDate.now(), LocalDate.now());

        @SuppressWarnings("unchecked")
        Map<Long, Integer> totalPoints = (Map<Long, Integer>) result.get("totalPoints");

        assertNotNull(result);
        assertTrue(totalPoints.isEmpty());
    }
}