package com.myBank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myBank.entities.Client;
/**
 * @author soufiane
 * 
 *  This is Sprint DATA JPA Repository, this interface represente the client repository
 *	This class represente DAO layer
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}
