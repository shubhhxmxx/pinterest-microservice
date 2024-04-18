package com.infy.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.Pin;

public interface PinRepository extends JpaRepository<Pin, BigInteger> {
	
	List<Pin> findAllByUserId(BigInteger userId);
	List<Pin> findAllByBoardId(BigInteger boardId);
	List<Pin> findAllByIsPrivate(Boolean isPrivate);
}
