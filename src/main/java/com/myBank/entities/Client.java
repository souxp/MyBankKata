package com.myBank.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author soufiane
 * 
 *         This is a JPA entity that represente client
 * 
 */
@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue
	private Long Id;
	private String name;
	private String email;
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Account> accounts;
	private static final long serialVersionUID = -30091652242241606L;

	public Client() {
		super();
	}

	public Client(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

}
