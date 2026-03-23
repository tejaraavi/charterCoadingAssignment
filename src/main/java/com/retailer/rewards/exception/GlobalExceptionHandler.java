package com.retailer.rewards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Global exception handler for the application.
 * 
 * This class uses Spring's {@link ControllerAdvice} to intercept exceptions
 * thrown across all controllers and provide consistent error responses. It
 * ensures that clients receive clean, structured JSON error messages instead of
 * raw stack traces.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles {@link CustomerNotFoundException} thrown when a requested customer
	 * does not exist in the system.
	 * 
	 * Returns an HTTP 404 (Not Found) response with a JSON body containing the
	 * error message.
	 *
	 * @param ex the exception containing details about the missing customer
	 * @return a ResponseEntity with status 404 and a JSON error message
	 */

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> handleCustomerNotFound(CustomerNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
	}
}