package com.infy.webApp.controller;




import com.infy.webApp.entity.Transaction;
import com.infy.webApp.service.RewardService;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

	@Autowired
    private final RewardService rewardService;
	@Autowired
	private Environment environment;
	
    

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    // Save new transaction
    @PostMapping("/transaction")
    public String saveTransaction(@RequestBody @Valid Transaction transaction) {
    	String msg=environment.getProperty("API.TRANSACTION_ADDED_SUCCESS");
        return msg;
    }

    // Get all transactions
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return rewardService.getAllTransactions();
    }

    // Calculate rewards for date range
    @GetMapping("/calculate")
    public Map<String, Object> calculateRewards(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return rewardService.calculateRewards(start, end);
    }
}
