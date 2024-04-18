package com.infy.repo;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.infy.entity.User;



public interface UserRepository extends JpaRepository<User,String> {
	Optional<User> findByUserId(BigInteger id);
	Optional<User> findByEmail(String email);
} 