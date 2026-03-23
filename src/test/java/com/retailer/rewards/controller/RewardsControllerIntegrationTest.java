package com.retailer.rewards.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RewardsController}.
 * 
 * These tests use Spring Boot's test framework and {@link MockMvc} to simulate
 * HTTP requests to the rewards API endpoints. The goal is to verify that the
 * controller, service, and repository layers work together correctly and return
 * the expected JSON responses.
 */

@SpringBootTest
@AutoConfigureMockMvc
class RewardsControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Tests reward calculation for customer Alice (ID = 1).
	 * 
	 * Verifies that the total points and monthly points returned by the API match
	 * the expected values based on Alice's transactions.
	 *
	 * @throws Exception if the request or assertions fail
	 */

	@Test
	void testRewardsForAlice() throws Exception {
		mockMvc.perform(get("/api/rewards/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalPoints").value(115))
				.andExpect(jsonPath("$.monthlyPoints['JANUARY']").value(90))
				.andExpect(jsonPath("$.monthlyPoints['FEBRUARY']").value(25));
	}

	/**
	 * Tests reward calculation for customer Bob (ID = 2).
	 * 
	 * Verifies that the total points and monthly points returned by the API match
	 * the expected values based on Bob's transactions.
	 *
	 * @throws Exception if the request or assertions fail
	 */

	@Test
	void testRewardsForBob() throws Exception {
		mockMvc.perform(get("/api/rewards/2")).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalPoints").value(250))
				.andExpect(jsonPath("$.monthlyPoints['JANUARY']").value(250))
				.andExpect(jsonPath("$.monthlyPoints['MARCH']").value(0));
	}

	/**
	 * Tests the API response for a non-existent customer (ID = 999).
	 * 
	 * Verifies that the API returns a 404 status and a JSON error message when the
	 * requested customer does not exist in the system.
	 *
	 * @throws Exception if the request or assertions fail
	 */
	@Test
	void testRewardsForNonExistentCustomer() throws Exception {
		mockMvc.perform(get("/api/rewards/999")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Customer not found with id: 999"));
	}

}