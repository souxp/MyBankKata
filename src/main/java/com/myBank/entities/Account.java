package com.myBank.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author soufiane
 * 
 *         This is a JPA entity that represente an account This is an abstract
 *         class, each abtract class represente a table in database
 *
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dType")
public abstract class Account implements Serializable {

	private static final long serialVersionUID = -1003442112236313842L;
	@Id
	private String accountId;
	private Date dateOfCreation;
	private double balance;
	@ManyToOne
	@JoinColumn(name = "CODE_CLIENT")
	private Client client;
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private Collection<Operation> tansactions;

	public Account(String accountId, Date dateOfCreation, double balance,
			Client client) {
		super();
		this.accountId = accountId;
		this.dateOfCreation = dateOfCreation;
		this.balance = balance;
		this.client = client;
	}

	public Account() {
		super();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getTransaction() {
		return tansactions;
	}

	public void setOperations(Collection<Operation> operations) {
		this.tansactions = operations;
	}

}
