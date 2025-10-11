package com.infy.webApp.dto;




import lombok.Data;


import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
@Data
public class TransactionsDTO {
 private Integer id;
   @NotBlank(message = "{customer.name.required}")
   @Size(min = 2, max = 50, message = "{customer.name.size}")
   @NotBlank(message = "{CUSTOMER_NAME_ABSENT}")
   private String customerName;
   @NotNull(message = "AMOUNT_REQUIRED")
   @DecimalMin(value = "1.0", message = "Amount must be greater than 0")
   private Double amount;
   @NotNull(message = "Date should be present")
   @PastOrPresent(message ="{DATE_PAST_PRESENT}")
   private LocalDate transactionDate;
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