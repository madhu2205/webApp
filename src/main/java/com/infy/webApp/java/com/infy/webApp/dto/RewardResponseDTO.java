package com.infy.webApp.dto;


import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardResponseDTO {
   private String customerId;
   private Map<String, Integer> monthlyPoints;
   private int totalPoints;
   private List<TransactionsDTO> transactions;

  
}
