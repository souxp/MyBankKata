package com.myBank.exceptions;

import org.apache.log4j.Logger;

public class BankAccountNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger
			.getLogger(BankAccountNotFoundException.class);

	public BankAccountNotFoundException() {
		logger.info("Bank account not found!!!");
	}

}
