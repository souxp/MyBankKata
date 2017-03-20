package com.myBank.exceptions;

import org.apache.log4j.Logger;

public class InsufficientBankBalanceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger
			.getLogger(InsufficientBankBalanceException.class);

	public InsufficientBankBalanceException() {
		logger.info("Insufficient bank balance");
	}

}
