package com.infy.webApp.dto;

import java.util.Map;

import lombok.Data;

@Data
public class RewardResponseDTO {
// private Integer customerId;
private String customerName;
private int totalRewards;
private int totalPoints;
private Map<String,Integer> monthlyPoints;
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public int getTotalRewards() {
	return totalRewards;
}
public void setTotalRewards(int totalRewards) {
	this.totalRewards = totalRewards;
}
public int getTotalPoints() {
	return totalPoints;
}
public void setTotalPoints(int totalPoints) {
	this.totalPoints = totalPoints;
}
public Map<String, Integer> getMonthlyPoints() {
	return monthlyPoints;
}
public void setMonthlyPoints(Map<String, Integer> monthlyPoints) {
	this.monthlyPoints = monthlyPoints;
}
 

}
