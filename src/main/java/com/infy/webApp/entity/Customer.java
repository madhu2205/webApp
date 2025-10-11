package com.infy.webApp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
 

   private Integer customerId;
   private String name;
   private String email;
 

  
}












//
//package com.infy.webApp.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//import lombok.Data;
//
//@Entity
//@Data
//public class Rewards {
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Integer rewardId;
//
//   @OneToOne
//   @JoinColumn(name = "transaction_id")
//   private Transactions transaction;
//
//   private Integer pointsEarned;
//
//   // Getters and Setters
//}
//
//package com.infy.webApp.entity;
//
//import java.time.LocalDate;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//import lombok.Data;
//
//
//@Entity
//@Data
//public class Transactions {
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Integer id;
//
//   private String customerName;
//   private Double amount;
//   private LocalDate transactionDate;
////    @ManyToOne
////    @JoinColumn(name="customer_id")
////    private Customer customer;
//
// 
//}
