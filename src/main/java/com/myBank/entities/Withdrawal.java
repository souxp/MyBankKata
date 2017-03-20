package com.myBank.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author soufiane
 * 
 *         This is a JPA entity that represente a Withdrawal operation It's
 *         extends an Operation class with a 'W' as discriminator value in an
 *         Operation Table in dababase.
 * 
 */
@Entity
@DiscriminatorValue("W")
public class Withdrawal extends Operation {

	private static final long serialVersionUID = -782475608805157423L;

	public Withdrawal() {
		super();
	}

	public Withdrawal(Date dateOfOperation, double amount, Account compte) {
		super(dateOfOperation, amount, compte);
	}

}
