package com.example.demo.service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import
org.springframework.stereotype.Service;
import com.example.demo.model.Transaction;

@Service
public class RewardService {
	
	public int calculatePoints(double amount) {
		int points = 0;
		
		if (amount > 100 ) {
			points += (amount - 100) *2;
			points += 50;
		} else if (amount > 50) {
			points += (amount - 50);
			}
		
		return points;
	}

    public Map<String, Integer> calculateMonthlyRewards(List<Transaction> transactions) {

    Map<String, Integer> monthlyPoints = new HashMap<>();

    for (Transaction t : transactions) {
        int points = calculatePoints(t.getAmount());

        String month = t.getDate().getMonth().toString();

        monthlyPoints.put(month,
            monthlyPoints.getOrDefault(month, 0) + points);
    }

    return monthlyPoints;
    
    }
}