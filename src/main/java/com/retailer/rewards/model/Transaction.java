package com.retailer.rewards.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;



/**
 * Represents a purchase transaction made by a customer in the rewards program.
 * 
 * Each transaction records the amount spent and the date of purchase.
 * Transactions are linked to a specific customer and are used to calculate
 * reward points for that customer.
 */

@Entity
@Table(name = "transactions")
public class Transaction {

	/**
	 * Unique identifier for the transaction. This is the primary key and is
	 * auto-generated.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The monetary amount of the transaction.
	 */

	private Double amount;
	/**
	 * The date when the transaction occurred.
	 */
	private LocalDate date;

	/**
	 * The customer associated with this transaction.
	 * 
	 * Many transactions can belong to one customer. The foreign key is stored in
	 * the "customer_id" column.
	 */

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Transaction() {
	}

	public Transaction(Double amount, LocalDate date, Customer customer) {
		this.amount = amount;
		this.date = date;
		this.customer = customer;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}