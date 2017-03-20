package com.myBank.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myBank.entities.Operation;

/**
 * @author soufiane
 * 
 *  This is Sprint DATA JPA Repository, this interface represente an operation(transaction) a client can do a
 *  'transfer' of a 'withdrawal'
 *	This class represente DAO layer
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

	
	@Query("select o from Operation o where o.account.accountId=:x")
	public ArrayList<Operation> tansactionsHistory(@Param("x") String acountId);
}
