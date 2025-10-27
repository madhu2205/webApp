package com.infy.webApp.dto;




import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class TransactionsDTO {
   private Long transactionId;
   private Double amount;
   private LocalDate date;
   private int pointsEarned;

   
}

