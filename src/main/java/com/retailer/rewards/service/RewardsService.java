package com.retailer.rewards.service;

import com.retailer.rewards.exception.CustomerNotFoundException;
import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class responsible for calculating reward points based on customer
 * transactions and retrieving reward summaries.
 */
@Service
public class RewardsService {

	private final CustomerRepository customerRepository;
	private final TransactionRepository transactionRepository;

	/**
	 * Constructs the RewardsService with required repositories.
	 *
	 * @param customerRepository    repository for customer data
	 * @param transactionRepository repository for transaction data
	 */

	public RewardsService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}

	/**
	 * Calculates reward points for a given transaction amount.
	 * 
	 * Rule: - 1 point for every dollar spent over $50 - 2 points for every dollar
	 * spent over $100
	 *
	 * @param amount transaction amount
	 * @return reward points earned
	 */

	public int calculatePoints(double amount) {
		int points = 0;
		if (amount > 100) {
			points += (int) ((amount - 100) * 2) + 50; // 2 points over 100 + 1 point for 50–100
		} else if (amount > 50) {
			points += (int) (amount - 50);
		}
		return points;
	}

	/**
	 * Retrieves reward points for a specific customer. Throws
	 * CustomerNotFoundException if the customer does not exist.
	 *
	 * @param customerId ID of the customer
	 * @return map containing customerId, monthlyPoints, and totalPoints
	 */

	public Map<String, Object> getRewardsByCustomer(Long customerId) {
		// Check if customer exists
		if (!customerRepository.existsById(customerId)) {
			throw new CustomerNotFoundException(customerId);
		}

		List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

		Map<Month, Integer> monthlyPoints = transactions.stream().collect(Collectors
				.groupingBy(t -> t.getDate().getMonth(), Collectors.summingInt(t -> calculatePoints(t.getAmount()))));

		int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

		Map<String, Object> result = new HashMap<>();
		result.put("customerId", customerId);
		result.put("monthlyPoints", monthlyPoints);
		result.put("totalPoints", totalPoints);

		return result;
	}

	/**
	 * Retrieves reward summaries for all customers.
	 *
	 * @return list of maps containing rewards data for each customer
	 */

	public List<Map<String, Object>> getAllCustomerRewards() {
		return customerRepository.findAll().stream().map(c -> getRewardsByCustomer(c.getId()))
				.collect(Collectors.toList());
	}
}