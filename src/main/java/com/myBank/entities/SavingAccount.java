package com.myBank.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author soufiane
 * 
 *         This is a JPA entity that Saving Account It's extends an Account
 *         class with a 'SA' as discriminator value in an Account Table in
 *         dababase.
 * 
 */
@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double rate;

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public SavingAccount(String AccountId, Date dateOfCreation, double balance,
			double rate, Client client) {
		super(AccountId, dateOfCreation, balance, client);
		this.rate = rate;
	}

	public SavingAccount() {
		super();
	}

}
