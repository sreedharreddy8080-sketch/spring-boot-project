package com.example.demo.controller;

import java.util.*;
import java.time.LocalDate;
import com.example.demo.model.Transaction;

import com.example.demo.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/{amount}")
    public int 
    getPoints(@PathVariable ("amount") double amount) {
        return
     rewardService.calculatePoints(amount);
    }
@GetMapping
public Map<String, Object> getRewards() {

    List<Transaction> transactions = new ArrayList<>();

    transactions.add(new Transaction("C1", 120, LocalDate.now()));
    transactions.add(new Transaction("C1", 80, LocalDate.now().minusMonths(1)));
    transactions.add(new Transaction("C1", 60, LocalDate.now().minusMonths(2)));

    Map<String, Integer> monthly = rewardService.calculateMonthlyRewards(transactions);

    int total = monthly.values().stream().mapToInt(Integer::intValue).sum();

    Map<String, Object> response = new HashMap<>();
    response.put("customerId", "C1");
    response.put("monthlyPoints", monthly);
    response.put("totalPoints", total);

    return response;
   }
}