package com.zee.zee5app.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//Custom jpa method
	//we will not write any definition
	//Just only declaration
	Boolean existsByEmailAndContactNumber(String email , BigInteger bigInteger);
	
	Optional<User> findByUserName(String userName);
}
