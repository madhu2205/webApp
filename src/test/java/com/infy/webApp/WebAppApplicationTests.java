package com.infy.webApp;



import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import com.infy.webApp.dto.RewardResponseDTO;

import com.infy.webApp.dto.TransactionsDTO;
import com.infy.webApp.entity.Transactions;
import com.infy.webApp.exception.CustomerException;
import com.infy.webApp.repository.TransactionRepository;
import com.infy.webApp.service.RewardServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class RewardsServiceImplTest {

   @Mock

   private TransactionRepository transactionRepository;

   @InjectMocks

   private RewardServiceImpl rewardsService;

   @BeforeEach

   void setUp() {

       MockitoAnnotations.openMocks(this);

   }



   // calculatePoints() tests



   @Test

   void testCalculatePointsAbove100() {

       int points = rewardsService.calculatePoints(120.0);

       // (120-100)*2 + 50 = 90

       assertEquals(90, points);

   }

   @Test

   void testCalculatePointsBetween50And100() {

       int points = rewardsService.calculatePoints(70.0);

       // (70 - 50) = 20

       assertEquals(20, points);

   }

   @Test

   void testCalculatePointsBelow50() {

       int points = rewardsService.calculatePoints(40.0);

       assertEquals(0, points);

   }

   // ----------------------

   // registerCustomer() tests

   // ----------------------

   @Test

   void testRegisterCustomerSuccess() throws CustomerException {

       TransactionsDTO dto = new TransactionsDTO();

//        dto.setCustomerId(1);

       dto.setAmount(80.0);

       Transactions savedEntity = new Transactions();

       savedEntity.setId(10);

       when(transactionRepository.save(any(Transactions.class))).thenReturn(savedEntity);

       Integer result = rewardsService.registerCustomer(dto);

       assertEquals(10, result);

       verify(transactionRepository, times(1)).save(any(Transactions.class));

   }

   @Test

   void testRegisterCustomerThrowsException() {

   TransactionsDTO dto = null;

       assertThrows(CustomerException.class, () -> rewardsService.registerCustomer(dto));

       verify(transactionRepository, never()).save(any());

   }

   // ----------------------

   // getRewards() tests

   // ----------------------

   @Test

   void testGetRewardsSuccess() throws Exception {

       Transactions tx1 = new Transactions();

       tx1.setId(1);

       tx1.setCustomerName("John");

       tx1.setAmount(120.0);

       tx1.setTransactionDate(LocalDate.now());

       Transactions tx2 = new Transactions();

       tx2.setId(1);

       tx2.setCustomerName("John");

       tx2.setAmount(80.0);

       tx2.setTransactionDate(LocalDate.now());

       List<Transactions> txList = Arrays.asList(tx1, tx2);

       when(transactionRepository.findAll()).thenReturn(txList);

       List<RewardResponseDTO> response = rewardsService.calculateRewards();

       assertNotNull(response);

       assertFalse(response.isEmpty());

       assertEquals("John", response.get(0).getCustomerName());

   }

   @Test

   void testGetRewardsNoTransactions() throws Exception {

       when(transactionRepository.findAll()).thenReturn(Collections.emptyList());

       List<RewardResponseDTO> response = rewardsService.calculateRewards();

       assertTrue(response.isEmpty());

   }

}
