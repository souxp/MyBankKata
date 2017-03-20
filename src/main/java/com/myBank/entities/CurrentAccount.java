package com.myBank.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author soufiane
 * 
 *         This is a JPA entity that Current Account It's extends an Account
 *         class with a 'CA' as discriminator value in an Account Table in
 *         dababase.
 * 
 */
@Entity
@DiscriminatorValue("CA")
public class CurrentAccount extends Account {

	private static final long serialVersionUID = -6534641729377975043L;
	private double overdraft;

	public CurrentAccount(double overdraft) {
		super();
		this.overdraft = overdraft;
	}

	public CurrentAccount() {
		super();
	}
	public CurrentAccount(String accountId, Date dateOfCreation,
			double balance, double overdraft, Client client) {
		super(accountId, dateOfCreation, balance, client);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

}
