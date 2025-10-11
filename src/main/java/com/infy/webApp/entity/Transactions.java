package com.infy.webApp.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Transactions {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private String customerName;
   private Double amount;
   private LocalDate transactionDate;
//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public LocalDate getTransactionDate() {
	return transactionDate;
}
public void setTransactionDate(LocalDate transactionDate) {
	this.transactionDate = transactionDate;
}

 
}