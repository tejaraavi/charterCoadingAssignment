package com.retailer.rewards.exception;

/**
 * Exception thrown when a requested customer cannot be found in the system.
 * 
 * This is a runtime exception used by the service layer to indicate that the
 * provided customer ID does not exist in the database. It is caught by the
 * global exception handler to return a clean JSON error response with HTTP
 * status 404.
 */
public class CustomerNotFoundException extends RuntimeException {

	/**
	 * Constructs a new CustomerNotFoundException with a detailed message including
	 * the missing customer ID.
	 *
	 * @param id the ID of the customer that was not found
	 */

	public CustomerNotFoundException(Long id) {
		super("Customer not found with id: " + id);
	}
}