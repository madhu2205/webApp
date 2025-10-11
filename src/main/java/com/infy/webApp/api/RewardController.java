package com.infy.webApp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.webApp.dto.RewardResponseDTO;
import com.infy.webApp.dto.TransactionsDTO;

import com.infy.webApp.exception.CustomerException;

import com.infy.webApp.service.RewardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RewardController {

   @Autowired
   private RewardService rewardService;

 
@Autowired
private Environment environment;


   @GetMapping("/rewards")
   public ResponseEntity<List<RewardResponseDTO>> getRewards()throws CustomerException {
       return ResponseEntity.ok(rewardService.calculateRewards());
   }
   

   @PostMapping("/transactions")
     public ResponseEntity<String> createTransaction( @Valid @RequestBody TransactionsDTO transactionDTO)throws CustomerException {
        
  Integer transactionId=rewardService.registerCustomer(transactionDTO);
   String msg=environment.getProperty("API.TRANSACTION_ADDED_SUCCESS")+":"+transactionId;

        return new ResponseEntity<String>(msg, HttpStatus.CREATED);

     }

}