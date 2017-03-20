package com.myBank.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myBank.dao.ClientRepository;
import com.myBank.dao.AccountRepository;
import com.myBank.dao.OperationRepository;
import com.myBank.entities.Account;
import com.myBank.entities.CurrentAccount;
import com.myBank.entities.Operation;
import com.myBank.entities.Withdrawal;
import com.myBank.entities.Deposit;
import com.myBank.exceptions.BankAccountNotFoundException;
import com.myBank.exceptions.InsufficientBankBalanceException;
/**
 * @author soufiane
 * 
 *  This is a Service Layer
 *  we use this class to get access to all the operations of the application
 *  All methods are transactional
 */
@Service
@Transactional
public class BankServiceImpl implements IBankService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OperationRepository operationRepository;

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}

	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public OperationRepository getOperationRepository() {
		return operationRepository;
	}

	public void setOperationRepository(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	@Override
	public Account getAccount(String accountId)
			throws BankAccountNotFoundException {

		Account account = accountRepository.findOne(accountId);
		if (account == null)
			throw new BankAccountNotFoundException();
		return account;
	}
/**
    * A Deposit method add an amount to a balance.
    * @param accountId the id of an account.
    * @param amount the amount to deposit.
*/
	@Override
	public void deposit(String accountId, double amount)
			throws BankAccountNotFoundException {

		Account account = getAccount(accountId);
		Deposit transfer = new Deposit(new Date(), amount, account);
		operationRepository.save(transfer);
		account.setBalance(account.getBalance()+amount);
		accountRepository.save(account);
	}
/**
    * A withraw method , withraw an amount from an account balance.
    * in this method we check the overfradt amount
    * @param accountId the id of an account.
    * @param amount the amount to deposit.
*/
	@Override
	public void withdraw(String accountId, double amount)
			throws InsufficientBankBalanceException,
			BankAccountNotFoundException {
		Account account = getAccount(accountId);
		Double overdraft = 0d;
		if (account instanceof CurrentAccount)
			overdraft = ((CurrentAccount) account).getOverdraft();
		if (account.getBalance() + overdraft < amount)
			throw new InsufficientBankBalanceException();

		Withdrawal withrawal = new Withdrawal(new Date(), amount, account);
		operationRepository.save(withrawal);
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);
	}
/**
    * A transfer method , transer an amount from issuer account to a receiver.
    * in this method we check the overfradt amount
    * @param issuerAccount the issuer
    * @param receiverAccount the receiver
    * @param amount the amount to deposit.
*/
	@Override
	public void transfer(String issuerAccount, String receiverAccount,
			double amount) throws InsufficientBankBalanceException,
			BankAccountNotFoundException {

		withdraw(issuerAccount, amount);
		deposit(receiverAccount, amount);
	}
/**
	 * A tansactionsHistory method ,get the transactions history for a specific account.
	 *@param issuerAccount the issuer.
	 *@param receiverAccount the receiver
	 *@param amount the amount to deposit. 
*/
	@Override
	public ArrayList<Operation> tansactionsHistory(String accountId) {
		return operationRepository.tansactionsHistory(accountId);
	} 

	public BankServiceImpl(AccountRepository compteRepository,
			ClientRepository clientRepository,
			OperationRepository operationRepository) {
		super();
		this.accountRepository = compteRepository;
		this.clientRepository = clientRepository;
		this.operationRepository = operationRepository;
	}

	public BankServiceImpl() {
		super();
	}

}
