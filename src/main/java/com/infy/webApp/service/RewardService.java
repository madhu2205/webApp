package com.infy.webApp.service;

import java.util.List;


import com.infy.webApp.dto.RewardResponseDTO;
import com.infy.webApp.dto.TransactionsDTO;
import com.infy.webApp.exception.CustomerException;

public interface RewardService {
  public List<RewardResponseDTO> calculateRewards()throws CustomerException;
   
   public Integer registerCustomer(TransactionsDTO transactionDTO) throws CustomerException;
}
