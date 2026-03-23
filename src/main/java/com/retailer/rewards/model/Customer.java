package com.retailer.rewards.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;


/**
 * Represents a customer in the rewards program.
 * 
 * Each customer has a unique ID, a name, and a list of transactions.
 * Transactions are used to calculate reward points for the customer.
 */

@Entity
@Table(name = "customers")
public class Customer {

	/**
	 * Unique identifier for the customer. This is the primary key and is
	 * auto-generated.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Name of the customer.
	 */

	private String name;

	/**
	 * List of transactions associated with the customer.
	 * 
	 * Mapped by the "customer" field in the Transaction entity. Cascade ensures
	 * that when a customer is persisted or removed, their transactions are also
	 * updated accordingly.
	 */

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transaction> transactions;

	/**
	 * Default constructor required by JPA.
	 */

	public Customer() {
	}

	/**
	 * Constructs a new Customer with the given name.
	 *
	 * @param name the name of the customer
	 */
	public Customer(String name) {
		this.name = name;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}