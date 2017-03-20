package com.myBank.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author soufiane
 * 
 *         This is a JPA entity that represente an operation(transaction) as you
 *         can see this is an abstact class the implementation of this class are
 *         the classes "Deposit" and "Withrawal" wich represente the operations
 * 
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OPERATION", discriminatorType = DiscriminatorType.STRING)
public abstract class Operation implements Serializable {

	private static final long serialVersionUID = -785711023321473950L;
	@Id
	@GeneratedValue
	private Long Id;
	private Date dateOfOperation;
	private double amount;
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	public Operation() {
		super();
	}

	public Operation(Date dateOfOperation, double amount, Account account) {
		super();
		this.dateOfOperation = dateOfOperation;
		this.amount = amount;
		this.account = account;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getDateOfOperation() {
		return dateOfOperation;
	}

	public void setDateOfOperation(Date dateOfOperation) {
		this.dateOfOperation = dateOfOperation;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
