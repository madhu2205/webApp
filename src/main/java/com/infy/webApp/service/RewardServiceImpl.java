package com.infy.webApp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.webApp.dto.RewardResponseDTO;
import com.infy.webApp.dto.TransactionsDTO;
import com.infy.webApp.entity.Transactions;
import com.infy.webApp.exception.CustomerException;
import com.infy.webApp.repository.TransactionRepository;

@Service
public class RewardServiceImpl implements RewardService {

   @Autowired
   private TransactionRepository transactionRepository;
   ModelMapper model = new ModelMapper();

   @Override
   public List<RewardResponseDTO> calculateRewards()throws CustomerException {
       List<Transactions> transactions = transactionRepository.findAll();
       Map<String, Map<String, Integer>> customerMonthlyPoints = new HashMap<>();

       for (Transactions tx : transactions) {
           String customer = tx.getCustomerName();
           String month = tx.getTransactionDate().getMonth().toString();

           int points = calculatePoints(tx.getAmount());

           customerMonthlyPoints
               .computeIfAbsent(customer, k -> new HashMap<>())
               .merge(month, points, Integer::sum);
       }

       List<RewardResponseDTO> response = new ArrayList<>();
       for (Map.Entry<String, Map<String, Integer>> entry : customerMonthlyPoints.entrySet()) {
           RewardResponseDTO dto = new RewardResponseDTO();
           
           dto.setCustomerName(entry.getKey());
           dto.setMonthlyPoints(entry.getValue());
           dto.setTotalPoints(entry.getValue().values().stream().mapToInt(Integer::intValue).sum());
           response.add(dto);
       }

       return response;
   }

   public int calculatePoints(double amount) {
       int points = 0;
       if (amount > 100) {
           points += (int) ((amount - 100) * 2);
           points += 50; // 1 point per dollar between 50 and 100
       } else if (amount > 50) {
           points += (int) (amount - 50);
       }
       return points;
   }

@Override
public Integer registerCustomer(TransactionsDTO transactionDTO) throws CustomerException {
	// TODO Auto-generated method stub
	if(transactionDTO==null) {
		throw new CustomerException("Transaction data is missing");
		
	}
	Transactions entity=model.map(transactionDTO, Transactions.class);
	Transactions saved=transactionRepository.save(entity);
	return saved.getId();
	
}



}
