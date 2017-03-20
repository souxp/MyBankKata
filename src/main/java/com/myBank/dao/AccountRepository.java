package com.myBank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myBank.entities.Account;
/**
 * @author soufiane
 * 
 *  This is Sprint DATA JPA Repository, this interface represente the an Account repository
 *  This class represente DAO layer
 *
 */
public interface AccountRepository extends JpaRepository<Account, String> {

}
