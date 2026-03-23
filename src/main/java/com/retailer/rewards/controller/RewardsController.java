package com.retailer.rewards.controller;

import com.retailer.rewards.service.RewardsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing endpoints for rewards calculation.
 */
@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

	private final RewardsService rewardsService;

	/**
	 * Constructs the RewardsController with the RewardsService.
	 *
	 * @param rewardsService service used to calculate rewards
	 */
	public RewardsController(RewardsService rewardsService) {
		this.rewardsService = rewardsService;
	}

	/**
	 * Get rewards for a specific customer.
	 *
	 * @param customerId ID of the customer
	 * @return rewards summary as JSON
	 */

	@GetMapping("/{customerId}")
	public Map<String, Object> getCustomerRewards(@PathVariable Long customerId) {
		return rewardsService.getRewardsByCustomer(customerId);
	}

	/**
	 * Get rewards for all customers.
	 *
	 * @return list of rewards summaries as JSON
	 */
	@GetMapping
	public List<Map<String, Object>> getAllRewards() {
		return rewardsService.getAllCustomerRewards();
	}
}