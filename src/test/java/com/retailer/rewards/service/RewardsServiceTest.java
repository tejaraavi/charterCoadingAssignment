package com.retailer.rewards.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link RewardsService} class.
 * 
 * These tests validate the reward points calculation logic for different
 * transaction amounts, ensuring that the business rules are correctly applied.
 * The tests cover positive scenarios (valid amounts) as well as negative
 * scenarios (invalid or negative amounts).
 */

@SpringBootTest
public class RewardsServiceTest {

	@Autowired
	private RewardsService rewardsService;

	/**
	 * Tests reward calculation for an amount greater than 100.
	 * 
	 * Verifies that the service correctly applies the rule: - 2 points for every
	 * dollar over 100 - plus 1 point for every dollar between 50 and 100.
	 */
	@Test
	void testPointsForAmountOver100() {
		assertEquals(90, rewardsService.calculatePoints(120));
	}

	/**
	 * Tests reward calculation for an amount between 50 and 100.
	 * 
	 * Verifies that the service correctly awards 1 point for every dollar spent
	 * over 50.
	 */
	@Test
	void testPointsForAmountBetween50And100() {
		assertEquals(25, rewardsService.calculatePoints(75));
	}

	/**
	 * Tests reward calculation for an amount below 50.
	 * 
	 * Verifies that no points are awarded when the transaction amount is less than
	 * or equal to 50.
	 */
	@Test
	void testPointsForAmountBelow50() {
		assertEquals(0, rewardsService.calculatePoints(40));
	}

	/**
	 * Tests reward calculation for a large amount (e.g., 200).
	 * 
	 * Verifies that the service correctly applies both rules and calculates the
	 * total points accordingly.
	 */
	@Test
	void testPointsForLargeAmount() {
		assertEquals(250, rewardsService.calculatePoints(200));
	}

	/**
	 * Tests reward calculation for a negative amount.
	 * 
	 * Verifies that the service does not award points for invalid transaction
	 * amounts (negative values).
	 */
	@Test
	void testPointsForNegativeAmount() {
		assertEquals(0, rewardsService.calculatePoints(-50));
	}
}