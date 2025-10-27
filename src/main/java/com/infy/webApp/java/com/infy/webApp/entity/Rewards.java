package com.infy.webApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Rewards {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer rewardId;

   @OneToOne
   @JoinColumn(name = "transaction_id")
   private Transaction transaction;

   private Integer pointsEarned;

   
}
