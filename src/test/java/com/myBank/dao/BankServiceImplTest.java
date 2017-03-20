package com.myBank.dao;

import java.util.ArrayList;
import java.util.Date;








import org.assertj.core.internal.Arrays;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.myBank.services.BankServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.myBank.entities.Client;
import com.myBank.entities.Account;
import com.myBank.entities.CurrentAccount;
import com.myBank.entities.Operation;
import com.myBank.exceptions.BankAccountNotFoundException;
import com.myBank.exceptions.InsufficientBankBalanceException;

/**
 * @author soufiane
 * 
 * @DataJpaTest will configure an in-memory embedded database, scan for @Entity
 *              classes and configure Spring Data JPA repositories. It is also
 *              transactional and rollback at the end of each test. If we wanna
 *              disable transaction management
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BankServiceImplTest {

	private BankServiceImpl bankService;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		bankService = new BankServiceImpl();
		bankService.setClientRepository(clientRepository);
		bankService.setAccountRepository(accountRepository);
		bankService.setOperationRepository(operationRepository);

	}

	
	//test getAccount OK and BankAccountNotFoundException
	@Test(expected = BankAccountNotFoundException.class)
	public void testBankAccountNotFoundException()
			throws BankAccountNotFoundException {
		bankService.getAccount("1111");
	}
	//test getAccount OK
	@Test
	public void testgetAccount() throws BankAccountNotFoundException {
		Client client1 = new Client("sofia", "data@gmail.com");
		Account account1 = new CurrentAccount("1111", new Date(), 150d, 0, client1);
		Client client2 = new Client("sofiane", "data@gmail.com");
		Account account2 = new CurrentAccount("2222", new Date(), 200d, 0, client2);
		clientRepository.save(client1);
		accountRepository.save(account1);
		clientRepository.save(client2);
		accountRepository.save(account2);
		Account checkedCompte = bankService.getAccount("2222");
		assertThat(checkedCompte).hasFieldOrPropertyWithValue("accountId",
				"2222");
		assertThat(checkedCompte).hasFieldOrPropertyWithValue("balance", 200.0);
	}

	//test deposit ok
	@Test
	public void testDepositOK() throws BankAccountNotFoundException {
		Client client = new Client("data", "data@gmail.com");
		clientRepository.save(client);
		Account account = new CurrentAccount("1111", new Date(), 150d, 0, client);
		accountRepository.save(account);
		bankService.deposit("1111", 50l);
		assertThat(bankService.getAccount("1111")).hasFieldOrPropertyWithValue(
				"balance", 200d);
	}
	//test transfer OK
	@Test
	public void testTansferOK() throws BankAccountNotFoundException, InsufficientBankBalanceException {
		Client client1 = new Client("annie", "data@gmail.com");
		Account payer = new CurrentAccount("1111", new Date(), 150d, 0, client1);
		Client client2 = new Client("sofiane", "data@gmail.com");
		Account payee = new CurrentAccount("2222", new Date(), 200d, 0, client2);
		double amount=30d;
		clientRepository.save(client1);
		accountRepository.save(payer);
		clientRepository.save(client2);
		accountRepository.save(payee);
		bankService.transfer("1111", "2222", amount);
		Account checkedCompte1 = bankService.getAccount("1111");
		Account checkedCompte2 = bankService.getAccount("2222");
		assertThat(checkedCompte1).hasFieldOrPropertyWithValue("accountId",
				"1111").hasFieldOrPropertyWithValue("balance", 120.0);
		assertThat(checkedCompte2).hasFieldOrPropertyWithValue("accountId",
				"2222").hasFieldOrPropertyWithValue("balance", 230.0);
	}	
	//test tansactionHistory OK
	@Test
	public void testTransactionHistoryOK() throws BankAccountNotFoundException, InsufficientBankBalanceException {
		Client client1 = new Client("annie", "data@gmail.com");
		Account payer = new CurrentAccount("1111", new Date(), 750d, 0, client1);
		Client client2 = new Client("sofiane", "data@gmail.com");
		Account payee = new CurrentAccount("2222", new Date(), 200d, 0, client2);
		double amount=30d;
		clientRepository.save(client1);
		accountRepository.save(payer);
		clientRepository.save(client2);
		accountRepository.save(payee);
		bankService.withdraw("1111", 10d);
		bankService.deposit("1111", 10d);
		bankService.withdraw("2222", 10d);
		bankService.deposit("2222", 10d);
		bankService.transfer("1111", "2222", amount);
		ArrayList<Operation> transactionHistory = bankService.tansactionsHistory("1111");
	    Assert.assertNotNull("List shouldn't be null", transactionHistory);
	    Assert.assertEquals("right size", 3, transactionHistory.size());
	}	
	//test withraw OK
	@Test
	public void testWhidrawOK() throws BankAccountNotFoundException, InsufficientBankBalanceException {
		Client client = new Client("data", "data@gmail.com");
		clientRepository.save(client);
		Account account = new CurrentAccount("1111", new Date(), 150d, 0, client);
		accountRepository.save(account);
		bankService.withdraw("1111", 50l);
		assertThat(bankService.getAccount("1111")).hasFieldOrPropertyWithValue(
				"balance", 100d);
	}	
	//test InsufficientBankBalanceException
	@Test(expected = InsufficientBankBalanceException.class)
	public void testInsufficientBankBalanceException() throws BankAccountNotFoundException, InsufficientBankBalanceException {
		Client client1 = new Client("annie", "data@gmail.com");
		Account payer = new CurrentAccount("1111", new Date(), 150d, 0, client1);
		Client client2 = new Client("sofiane", "data@gmail.com");
		Account payee = new CurrentAccount("2222", new Date(), 200d, 0, client2);
		double amount=300d;
		clientRepository.save(client1);
		accountRepository.save(payer);
		clientRepository.save(client2);
		accountRepository.save(payee);
		bankService.transfer("1111", "2222", amount);
	}	
}
