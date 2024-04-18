package com.infy.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.User;

public interface UserRepository extends JpaRepository<User,BigInteger> {

}
