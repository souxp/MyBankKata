package com.myBank.services;

import java.util.ArrayList;
import com.myBank.entities.Account;
import com.myBank.entities.Operation;
import com.myBank.exceptions.BankAccountNotFoundException;
import com.myBank.exceptions.InsufficientBankBalanceException;
/**
 * @author soufiane
 * 
 *  This is a Service Layer
 *  we use this class to get access to all the operations of the application
 */
public interface IBankService {

	public Account getAccount(String accountId)
			throws BankAccountNotFoundException;

	public void deposit(String accountId, double amount)
			throws BankAccountNotFoundException;

	public void withdraw(String accountId, double amount)
			throws InsufficientBankBalanceException,
			BankAccountNotFoundException;

	public void transfer(String issuerAccount, String receiverAccount,
			double amount) throws InsufficientBankBalanceException,
			BankAccountNotFoundException;

	public ArrayList<Operation> tansactionsHistory(String accountId);

}
