package com.infy.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.Collaboration;

public interface CollaborationRepo extends JpaRepository<Collaboration, BigInteger> {
	 List<Collaboration> findAllByBoardId(BigInteger boardId);
	 
	
	List<Collaboration> findAllByFollowerId(BigInteger followerId);
}
