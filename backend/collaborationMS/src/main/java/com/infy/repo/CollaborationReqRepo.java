package com.infy.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.CollaborationRequest;

public interface CollaborationReqRepo extends JpaRepository<CollaborationRequest, BigInteger> {

	List<CollaborationRequest> findAllByUserId(BigInteger userId);
	CollaborationRequest findByBoardIdAndFollowerId(BigInteger boardId,BigInteger followerId);
}
