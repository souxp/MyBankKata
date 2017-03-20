package com.myBank.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author soufiane
 * 
 *         This is a JPA entity that represente a deposit operation(transaction)
 * 
 */
@Entity
@DiscriminatorValue("T")
public class Deposit extends Operation {

	private static final long serialVersionUID = -6583088878047860907L;

	public Deposit() {
		super();
	}

	public Deposit(Date dateOfOperation, double amount, Account account) {
		super(dateOfOperation, amount, account);
	}

}
